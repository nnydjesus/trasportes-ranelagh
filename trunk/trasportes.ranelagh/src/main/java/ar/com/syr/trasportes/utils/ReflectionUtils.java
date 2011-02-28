package ar.com.syr.trasportes.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections15.Predicate;
import org.apache.commons.lang.StringUtils;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.com.mindset.flexy.annotations.flex.serializer.SerializationStrategy;
import ar.com.mindset.flexy.annotations.flex.serializer.Through;
import ar.com.mindset.flexy.exception.ApplicationException;
import ar.com.mindset.flexy.exception.NonBusinessException;
import ar.com.mindset.flexy.util.ASObjectUtils;
import ar.com.mindset.flexy.util.TypedClass;
import ar.com.mindset.flexy.util.predicate.SuperclassPredicate;
import flex.messaging.io.amf.ASObject;
 
/**
 * @author Ronny
 *
 */
public class ReflectionUtils {
	

    /**
     * Instantiate class and handles reflection exceptions
     */
    public static <T> T instanciate(final Class<T> clazz) {
        return instanciate(clazz, new Object[0]);
    }


    /**
     * Instantiate class and handles reflection exceptions
     */
    public static <T> T instanciate(final Class<T> clazz, final Object... _params) {
        try {
            if ( _params != null && _params.length > 0 ) {
                final Class[] _types = new Class[_params.length];

                for ( int _i = 0; _i < _types.length; _i++ ) {
                    _types[_i] = _params[_i].getClass();
                }

                return clazz.getConstructor(_types).newInstance(_params);
            }
            else {
                return clazz.newInstance();
            }
        }
        catch ( final Exception e ) {
            throw new NonBusinessException(e);
        }
    }


    public static <T> T instanciatePrivate(final Class<T> clazz) {
        try {
            final Constructor<T> constructor = clazz.getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);
            return constructor.newInstance(new Object[0]);
        }
        catch ( final Exception e ) {
            throw new NonBusinessException(e);
        }
    }


    /**
     * Class for name, instead of ClassNotFoundException returns null when class cannot be
     * loaded.
     */
    public static Class classForName(final String className) {
        try {
            return Class.forName(className);
        }
        catch ( final ClassNotFoundException e ) {
            return null;
        }

    }


    /**
     * Returns the real class of an object.
     */
    public static Class getRealClass(final Object object) {
        return HibernateProxyHelper.getClassWithoutInitializingProxy(object);
    }

    /**
     * Uff... es medio feo, pero este método me retorna el typedClass de un Objeto
     *
     * @param _object Object del que quiero saber el TypedClass
     * @param _typedClass TypedClass default
     * @return
     */
    public static TypedClass getTypedClass(final Object _object, final TypedClass _typedClass) {
        if(ASObjectUtils.isASObject(_object)) {

            try {
                final Class _class = Class.forName(((ASObject) _object).getType());
                return new TypedClass(_class);
            } catch (final Exception ex) {
                return _typedClass;
            }

        } else {
            return _typedClass;
        }
    }


    /**
     * Returns every declared field on the specified class.
     */
    public static List<Field> getAllFields(final Class clase) {
        return getAllFields(clase, null);
    }


    /**
     * Returns every declared field on the specified class.
     */
    public static List<Field> getAllFields(final Class clase, final Predicate<Field> _fieldPredicate) {
        final List<Field> result = new ArrayList<Field>();

        if ( clase != null ) {
            for ( final Field each : clase.getDeclaredFields() ) {
                if ( _fieldPredicate == null || _fieldPredicate.evaluate(each) ) {
                    result.add(each);
                }
            }

            for ( final Field each : getAllFields(clase.getSuperclass()) ) {
                if ( _fieldPredicate == null || _fieldPredicate.evaluate(each) ) {
                    result.add(each);
                }
            }
        }
        return result;
    }


    /**
     * Reads a binded generic from a field
     */
    public static Class readGeneric(final Field field, final int index) {
        final Type genericType = field.getGenericType();
        return readGeneric((ParameterizedType) genericType, index);
    }

    public static Class[] readGenerics(final Field field) {
        final Type genericType = field.getGenericType();
        
        if(genericType instanceof ParameterizedType) {
        	final Type[] readGenerics = readGenerics((ParameterizedType) genericType);
        	final Class[] ret = new Class[readGenerics.length];
        	for (int i = 0; i < readGenerics.length; i++) {
        		final Type type = readGenerics[i];
        		ret[i] = (Class) type;
        	}
        	return ret;
        } else {
        	return new Class[] {};
        }
    }


    public static Type[] readGenerics(final ParameterizedType genericType) {
        return genericType.getActualTypeArguments();
    }


    public static Class readGeneric(final ParameterizedType genericType, final int index) {
        final Type actualType = readGenerics(genericType)[index];
        return (Class) actualType;
    }


    /**
     * Invokes a method and handle reflection exceptions. It uses variable
     * parameter syntax.
     */
    public static Object invoke(final Object object, final Method method, final Object... params) {
        return invokeNotDynarg(object, method, params);
    }


    public static Object invokeNotDynarg(final Object object, final Method method, final Object[] params) {
        try {
            return method.invoke(object, params);
        }
        catch ( final InvocationTargetException e ) {
            final Throwable cause = e.getCause();
            if ( cause instanceof ApplicationException ) {
                throw (ApplicationException) cause;
            }
            else {
                throw new NonBusinessException("Exception during method invocation", e);
            }
        }
        catch ( final Exception e ) {
            throw new NonBusinessException("Cannot invoke method", e);
        }
    }


    public static Method getPrivateMethod(final Class declaringClass, final String methodName, final Class... propertyType) {
        if ( declaringClass == null ) {
            return null;
        }

        final Method[] declaredMethods = declaringClass.getDeclaredMethods();
        for ( final Method method : declaredMethods ) {
            if ( method.getName().equals(methodName) && Arrays.equals(method.getParameterTypes(), propertyType) ) {
                return method;
            }
        }
        return getPrivateMethod(declaringClass.getSuperclass(), methodName, propertyType);
    }


    public static Method getPrivateMethodWithParametersQty(final Class declaringClass, final String methodName, final int qty) {
        if ( declaringClass == null ) {
            return null;
        }

        final Method[] declaredMethods = declaringClass.getDeclaredMethods();
        for ( final Method method : declaredMethods ) {
            if ( method.getName().equals(methodName) && method.getParameterTypes().length == qty ) {
                return method;
            }
        }
        return getPrivateMethodWithParametersQty(declaringClass.getSuperclass(), methodName, qty);
    }


    public static Field getField(final Class clazz, final String string) {
        try {
            return clazz.getDeclaredField(string);
        }
        catch ( final Exception e ) {
            throw new NonBusinessException("Cannot recover field " + string + " from class " + clazz, e);
        }
    }


    public static Object readField(final Object target, final Field field) {
        try {
            return field.get(target);
        }
        catch ( final Exception e ) {
            throw new NonBusinessException("Cannot get field value", e);
        }
    }


    public static void writeField(final Object target, final Field field, final Object value) {
        try {
            field.set(target, value);
        }
        catch ( final Exception e ) {
            throw new NonBusinessException("Cannot set field value", e);
        }
    }

    public static void writePorperty(final Object target, final Field field, final Object value) {
        final SerializationStrategy strategy = field.getAnnotation(SerializationStrategy.class);
        final Through through = strategy != null ? strategy.access() : Through.FIELD;
        through.write(target, field, value);
    }

    public static Object readProperty(final Object target, final Field field) {
        final SerializationStrategy strategy = field.getAnnotation(SerializationStrategy.class);
        final Through through = strategy != null ? strategy.access() : Through.FIELD;
        return through.read(target, field);
    }


    public static Field getFieldFromAll(final Class clazz, final String propertyName) {
        final List<Field> all = getAllFields(clazz);
        for ( final Field field : all ) {
            if ( field.getName().equals(propertyName) ) {
                return field;
            }
        }
        return null;
    }


    public static <T extends Annotation> Collection<T> getAnnotationInherited(final Class targetClass, final Class<T> annotationClass) {
        final Collection<T> result = new ArrayList<T>();

        final T annotation = (T) targetClass.getAnnotation(annotationClass);
        if ( annotation != null ) {
            result.add(annotation);
        }

        final Class superClass = targetClass.getSuperclass();
        if ( superClass != null ) {
            result.addAll(getAnnotationInherited(superClass, annotationClass));
        }
        return result;
    }


    public static Class getNullSafeClass(final Object _object) {
        return _object == null ? null : _object.getClass();
    }

    public static void invokeSetter(final Object _object, final String _name, final Object _value) {
        if(_object == null) {
            return;
        }

        final Method[] declaredMethods = _object.getClass().getDeclaredMethods();

        try {

            for ( final Method method : declaredMethods ) {
                if ( method.getName().equals("set" + StringUtils.capitalize(_name)) ) {
                    ReflectionUtils.invoke(_object, method, _value);
                    return;
                }
            }

        } catch(final Exception ex) {
            throw new NonBusinessException("Se ha producido un error invocando " + "set" + StringUtils.capitalize(_name), ex);
        }

        throw new NonBusinessException("No se ha encontrado el setter " + "set" + StringUtils.capitalize(_name));
        
    }

    
    public static boolean isClassDescendant(Class downer, Class upper) {
    	return new SuperclassPredicate(upper).evaluate(downer);
    }


	public static Object invokeMethod(Object model, String actionName) {
		try {
			return model.getClass().getMethod(actionName, new Class[]{}).invoke(model, new Object[]{});
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void invokeMethod(Object model, String actionName, Class c, Object... args) {
		try {
			model.getClass().getMethod(actionName, c).invoke(model, args);
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
