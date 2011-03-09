package ar.com.nny.base.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Characteristic {

    String tableName() default "";


    boolean allowSubCharacteristics() default true;


    CharacteristicValue[] values();

}
