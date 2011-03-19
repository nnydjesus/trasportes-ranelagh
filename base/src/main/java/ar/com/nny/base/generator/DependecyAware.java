package ar.com.nny.base.generator;

import java.util.Collection;

/**
 * @author hernan.liendo@gmail.com
 */
public interface DependecyAware {

    public Collection<DependecyAware> dependencies();

}
