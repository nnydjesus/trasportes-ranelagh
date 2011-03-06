package ar.com.nny.java.base.dao;

import java.lang.annotation.*;

/**
 * This annotation indicates that the value of the current field must be
 * accessed through the specified methods for serializing and deserializing.
 *
 * @author Claudio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SerializationStrategy {

    /**
     * This indicates the strategy through which the field will be accessed.
     * The default value is FIELD access.
     *
     * @see ar.com.mindset.flexy.annotations.flex.serializer.Through#read(Object, java.lang.reflect.Field)
     * @see ar.com.mindset.flexy.annotations.flex.serializer.Through#write(Object, java.lang.reflect.Field, Objec)
     */
    public Through access() default Through.FIELD;


    /**
     * For collection properties this method will be used to add elements
     * if not empty.
     */
    public String addMethod() default "";


    /**
     * For collection properties this method will be used to remove elements
     * if not empty.
     */
    public String removeMethod() default "";

}
