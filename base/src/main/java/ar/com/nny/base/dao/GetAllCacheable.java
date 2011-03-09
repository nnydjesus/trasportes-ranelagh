package ar.com.nny.base.dao;

import java.lang.annotation.*;

/**
 * Persistent objects demarcated with this annotation has their getAll query
 * cached.
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface GetAllCacheable {

}
