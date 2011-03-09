package ar.com.nny.base.configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(value = RetentionPolicy.RUNTIME)
public @interface CharacteristicValue {

    String code();


    String name();


    String codeSuperCharacteristic() default "";

}
