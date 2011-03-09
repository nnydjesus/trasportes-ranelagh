package ar.com.nny.base.annotations.persistence;

import java.lang.annotation.*;

/**
 * This annotation is used to mark custom types with the converter
 * It should have the converter class as parameter.
 * <p/>
 * For example @Converter(MyCustomTypeConverter.class)
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Converter {

    @SuppressWarnings("unchecked")
	public Class value();
}
