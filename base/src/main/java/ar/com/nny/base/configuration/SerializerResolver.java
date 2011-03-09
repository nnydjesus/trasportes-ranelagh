package ar.com.nny.base.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

/**
 * This class is a registry of all existent flex serializers.
 *
 * @author Claudio Fernandez
 */
public class SerializerResolver {

    private static SerializerResolverProvider provider = new FlexySerializerResolverProvider();
    
    
    private static Map<Class, Serializer> serializersCache = new HashMap<Class, Serializer>();


    private static List<Serializer> getSerializers() {
        return provider.getSerializers();
    }


    /**
     * Return the serializer for a given object.
     */
    public static <T, D> Serializer<T, D> getSerializer(final T object) {
        final Class clazz = object != null ? object.getClass() : null;
        
        if(!serializersCache.containsKey(clazz)) {
        	synchronized (serializersCache) {
        		serializersCache.put(clazz, CollectionUtils.find(getSerializers(), new Predicate<Serializer>() {
        			public boolean evaluate(final Serializer serializer) {
        				return serializer.canSerialize(clazz);
        			}
        		}));
			}
        }
        return serializersCache.get(clazz);
    }


    /**
     * Return the deserializer (serializer) for a given object that is going to
     * be transformed to a desired class.
     */
    public static <T, D> Serializer<T, D> getDeserializer(final D object, final TypedClass desiredClass) {
        final Class clazz = object != null ? object.getClass() : null;
        return CollectionUtils.find(getSerializers(), new Predicate<Serializer>() {
            public boolean evaluate(final Serializer serializer) {
                return serializer.canDeserialize(clazz, desiredClass);
            }
        });
    }

}
