package ar.com.nny.base.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.com.nny.base.dao.SerializationStrategy;

/**
 * @author Rodrigo Merino
 * Entities marked with this annotation are not registered in the changelog when they change or are created.
 * Thus, they aren't sent to the client on such circumstances.
 * <b>Warning</b>: If an entity is serialized to travel to the client and it references an instance of an annotated class, it will be sent to the client unless properly annotated with {@link SerializationStrategy}.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NotSendChanges {

}
