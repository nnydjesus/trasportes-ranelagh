package ar.com.nny.base.configuration;

import java.util.Collection;

/**
 * @author hernan.liendo@gmail.com
 */
public interface DependecyAware {

    public Collection<DependecyAware> dependencies();

}
