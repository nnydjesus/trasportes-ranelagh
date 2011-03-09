package ar.com.nny.base.initialData;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.com.nny.base.configuration.InitialDataGenerator;

/**
 * Declare dependencies between data generators.
 *
 * @author Claudio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataGeneratorDepends {

    public Class<? extends InitialDataGenerator>[] value();
}
