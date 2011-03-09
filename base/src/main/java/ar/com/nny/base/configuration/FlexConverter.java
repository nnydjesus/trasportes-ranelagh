package ar.com.nny.base.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.nny.base.exception.NonBusinessException;
import ar.com.nny.base.utils.BusinessObject;


/**
 * This is the Flex2Java and Java2Flex transformation Facade.
 * Here all the transformation process is being handled. Every
 * transformation is graph aware so cyclic relationships be welcome =D
 * <p/>
 * A Flex converter is not context independent. It should
 * be used for one transformation only.
 *
 * @author Claudio Fernandez
 */
public class FlexConverter<T, D> {

    public static final StandardCriteria STANDARD_CRITERIA = new StandardCriteria();
    private static final Log LOGGER = LogFactory.getLog(FlexConverter.class);

    private Map<T, D> serializeContext = new IdentityHashMap<T, D>();
    private Map<D, T> deserializeContext = new IdentityHashMap<D, T>();
    private Map<D, T> afterDeserializeContext = new IdentityHashMap<D, T>();
    private Set<BusinessObject> synchSafeObjects = new HashSet<BusinessObject>();
    private FetchCriteria graphLimiterCriteria = STANDARD_CRITERIA;

    private Stack<D> conversionStack = new Stack<D>();
    private Stack<Object> embeddedStack = new Stack<Object>();

    /**
     * This method should be called to transform a java object into
     * a flex representation.
     */
    public D serialize(final T object) {
        LOGGER.debug("Starting to serialize object [" + safeToString(object) + "]");
        //If its already on conversion context then retrieve it.
        if ( serializeContext.containsKey(object) ) {
            return serializeContext.get(object);
        }

        final Serializer<T, D> serializer = SerializerResolver.getSerializer(object);
        return serializer.serialize(object, this);
    }


    /**
     * This method should be called to transform a flex representation into
     * a java object.
     */
    public T deserialize(final D object, final TypedClass desiredClass, final Object parent) {
        this.conversionStack.push(object);
        final T result = this.deserializeRaw(object, desiredClass, parent);
        this.conversionStack.pop();

        if ( desiredClass != null && result != null
        		&& !desiredClass.rawClass().isAssignableFrom(result.getClass())
        		&& !desiredClass.rawClass().isPrimitive() ) {
            throw new NonBusinessException(
            		"Flexy convertion error. Cannot coerce object [" + safeToString(object) + "]" +
            		" to class [" + desiredClass.rawClass().getName() + "]." +
            		" The result of convertion was of class [" + result.getClass().getName() + "]");
        }

        return result;
    }


    private T deserializeRaw(final D object, final TypedClass desiredClass, final Object parent) {
        LOGGER.debug("Starting to deserialize object [" + safeToString(object) + "] to class [" + desiredClass + "]");
        if ( deserializeContext.containsKey(object) ) {
            return deserializeContext.get(object);
        }

        final Serializer<T, D> serializer = SerializerResolver.getDeserializer(object, desiredClass);
        return serializer.deserialize(object, desiredClass, this, parent);
    }



	public void afterDeserialized(T object, Object parent) {
        LOGGER.debug("Processing after deserialization object [" + safeToString(object) + "]");
        if ( afterDeserializeContext.containsKey(object) ) {
            return;
        }

        final Serializer<T, D> serializer = SerializerResolver.getSerializer(object);
        serializer.afterDeserialized(object, this, parent);
	}


    /**
     * This is just implemented to avoid infinite recursion in toStrings =S.
     * Its a funny error
     */
    private String safeToString(final Object object) {
        if ( object == null ) {
            return "null";
        }
        else if ( object instanceof Map ) {
            return "Map";
        }
        else if ( object instanceof Collection ) {
            return "Collection";
        }
        else {
            return object.toString();
        }
    }


    /**
     * Register an object as already serialized.
     */
    public void addToSerializeContext(final T object, final D result) {
        this.serializeContext.put(object, result);
    }

    /**
     * Register an object as already deserialized.
     */
    public void addToDeserializeContext(final D object, final T result) {
    	this.deserializeContext.put(object, result);
    }
    

    public boolean isSynchSafe(final BusinessObject po) {
        return this.synchSafeObjects.contains(po);
    }


    public void markSynchSafe(final BusinessObject po) {
        this.synchSafeObjects.add(po);
    }


    public FetchCriteria getGraphLimiterCriteria() {
        return this.graphLimiterCriteria;
    }


    public void setGraphLimiterCriteria(final FetchCriteria criteria) {
        this.graphLimiterCriteria = criteria;
    }


    public void needToCheckStateVersion(final BusinessObject bo) {
        final Map encoded = (Map) this.conversionStack.peek();

        final Number stateVersion = (Number) encoded.get("stateVersion");
        bo.checkVersionAgainst(stateVersion);
    }

    public void pushEmbedded(final Object _object) {
        embeddedStack.push(_object);
    }

    public Object popEmbedded() {
        return embeddedStack.pop();
    }

    public Object peekEmbedded() {
        return embeddedStack.empty() ? null : embeddedStack.peek();
    }


	public D notLoadedValue(final T object) {
        final Serializer<T, D> serializer = SerializerResolver.getSerializer(object);
        return serializer.notLoadedValue(object, this);
	}

}
