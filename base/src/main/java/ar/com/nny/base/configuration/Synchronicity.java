package ar.com.nny.base.configuration;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Synchronicity {

    public SynchronicityType value();

}
