package ar.com.nny.base.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Query (HQL) to be executed when rebuilding the lucene index for the annotated type.
 * It should declare a 'this' alias in the from clause.
 * It shouldn't have any where or order by clauses.
 * @author rodro
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BuildIndexQuery {
	public String value();
}
