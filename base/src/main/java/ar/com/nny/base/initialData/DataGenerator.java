package ar.com.nny.base.initialData;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.com.nny.base.configuration.InitialDataGenerator;

/**
 * This annotation is used to demarcate which java class
 * should be used as the initial data generator associated
 * to the current persistent class
 *
 * @author Claudio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataGenerator {

    public Class<? extends InitialDataGenerator> value();
}
