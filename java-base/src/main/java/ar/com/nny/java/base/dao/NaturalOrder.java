package ar.com.nny.java.base.dao;

import java.lang.annotation.*;

import ar.com.nny.java.base.annotations.persistence.OrderType;

/**
 * Indicates that the current field should should be used to naturally order
 * the given field when getAll operations are performed.
 *
 * @author Claudio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface NaturalOrder {

    public OrderType orderType() default OrderType.ASC;
}
