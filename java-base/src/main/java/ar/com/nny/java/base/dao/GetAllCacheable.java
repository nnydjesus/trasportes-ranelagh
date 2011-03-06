package ar.com.nny.java.base.dao;

import java.lang.annotation.*;

/**
 * Persistent objects demarcated with this annotation has their getAll query
 * cached.
 *
 * @author Claudio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface GetAllCacheable {

}
