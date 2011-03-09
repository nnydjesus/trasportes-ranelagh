package ar.com.nny.base.configuration;

import java.util.Collection;
import java.util.List;

import ar.com.nny.base.utils.PersistentObject;

/**
 * Every module should have a module descriptor that extends this
 * class.
 *
 * @author Claudio
 */
public abstract class AbstractModuleDescriptor {

    /**
     * Every module must register its persistent classes inside the given list.
     * The order in which the classes are registered is used to determine the order in which
     * the associated initial data generators will be run.
     *
     * @since 1.4.3 this method is no longer abstract, and ModuleDescriptors are not
     *        instructed to implement it. TypeInferencer do the job of detecting PersistentClasses
     *        into the classpath.
     */
    public void registerPersistentClasses(final Collection<Class<? extends PersistentObject>> list) {
    }


    /**
     * The dependency injection framework reads this information.
     * Injectors are used like injection-commands that applies over a newly created managed
     * object.
     */
    public void registerInjectors(final List<Injector> list) {
    }


    /**
     * Flex communication framework reads this information.
     * Serializers are used to serialize / deserialize an object of a given type.
     */
    public void registerFlexSerializers(final List<Serializer> list) {
    }


//    /**
//     * Flex communication framework reads this information.
//     * Each BusinessObjectSynchronizers tells the framework how a property (that matches
//     * to a given condition) should be written into a business object.
//     */
//    public void registerBusinessObjectSynchronizers(final List<Synchronizer> list) {
//    }


//    /**
//     * Administrative actions to be displayed in the flexy administrative console.
//     */
//    public void registerAdministrativeAction(final List<AdministrativeAction> list) {
//    }


    /**
     * Objects that call session.enable filter to apply filter for objects that include the Filter annotation.
     */
    public void registerFilterEnablers(final List<FilterEnabler> list) {
    }
}
