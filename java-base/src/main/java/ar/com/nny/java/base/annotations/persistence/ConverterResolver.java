package ar.com.nny.java.base.annotations.persistence;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

/**
 * This class is a registry of custom types and its converters.
 *
 * @author Gunther Schneider
 */
public class ConverterResolver {

    public static Type getTypeFor(Object object) {
        return (object == null) ? null : getTypeFor(object.getClass());
    }


    public static Type getTypeFor(Class c) {
        if ( !c.isAnnotationPresent(Converter.class) ) {
            return null;
        }
        Converter converterAnnotation = (Converter) c.getAnnotation(Converter.class);
        Class converterClass = converterAnnotation.value();
        return Hibernate.custom(converterClass);
    }

}
