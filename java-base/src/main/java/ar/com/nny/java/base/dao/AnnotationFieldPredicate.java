package ar.com.nny.java.base.dao;

import org.apache.commons.collections15.Predicate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper for common usage.
 *
 * @author hernan.liendo@gmail.com
 */
public final class AnnotationFieldPredicate implements Predicate<Field> {

    private final List<Class> annotations = new ArrayList<Class>();


    public AnnotationFieldPredicate(final Class... _annotations) {
        annotations.addAll(Arrays.asList(_annotations));
    }


    public boolean evaluate(final Field _field) {
        // no se puede usar CollectionUtils.containsAny porque una annotation no es equals a la otra :( (una es proxied)
        for ( final Class _wantedAnnotation : annotations ) {
            if ( _field.isAnnotationPresent(_wantedAnnotation) ) {
                return true;
            }
        }
        return false;
    }
}
