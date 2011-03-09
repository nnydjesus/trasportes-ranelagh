package ar.com.nny.base.configuration;

import java.lang.annotation.*;

/**
 * This annotation identifies which field is the business label
 * of an object. The business label will be use as object label
 * inside a combo list or other component.
 *
 * @author Claudio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BusinessLabel {

}
