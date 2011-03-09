package ar.com.nny.base.configuration;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AlternateSessionFactory {

    public String value();

}
