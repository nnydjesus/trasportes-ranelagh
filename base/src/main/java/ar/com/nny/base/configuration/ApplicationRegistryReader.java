package ar.com.nny.base.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;

/**
 * Read the moduleDescriptors for each module configured in the application and
 * returns information about all of them.
 *
 */
public class ApplicationRegistryReader {

    private static final Log LOGGER = LogFactory.getLog(ApplicationRegistryReader.class);
    public static final String STANDARD_BASE_PACKAGE = "ar.com.nny.base";

    private static ApplicationRegistryReader INSTANCE;

    private TypeInferencer inferencer = new TypeInferencer();


    public static ApplicationRegistryReader getInstance() {
        if ( INSTANCE == null ) {
//            INSTANCE = (ApplicationRegistryReader) Enhancer.create(ApplicationRegistryReader.class, new CachedMethodInterceptor());
        	INSTANCE = new ApplicationRegistryReader();
        }
        return INSTANCE;
    }


    public static String encodePackage(final String packageName) {
        return packageName.replaceAll("\\.", "\\.");
    }


    /**
     * Return all module descriptor instances.
     * <p/>
     * The application search for each module ModuleDescriptor class. ModuleDescriptor
     * classes should be named according this convention:
     * <p/>
     * [configuration:packagePrefix].[moduleName].ModuleDescriptor
     * <p/>
     * The system will ignore those modules whose ModuleDescriptor classes couldn't be
     * found.
     */
    protected List<AbstractModuleDescriptor> getAllModuleDescritors() {
//        final List<String> modules = FlexyConfiguration.getModules();
//        final String packagePrefix = FlexyConfiguration.getPackagePrefix();
    	 List<String> modules = new ArrayList<String>();
    	 modules.add("initialData");
        final List<AbstractModuleDescriptor> result = new ArrayList();

        for ( final String module : modules ) {
            LOGGER.debug("Loading module [" + module + "]");
            final String moduleName = STANDARD_BASE_PACKAGE + "." + module + ".ModuleDescriptor";
            final Class descriptorClass = ReflectionUtils.classForName(moduleName);
            if ( descriptorClass != null ) {
                final AbstractModuleDescriptor descriptor = (AbstractModuleDescriptor) ReflectionUtils.instanciate(descriptorClass);
                result.add(descriptor);
                LOGGER.debug("Module [" + moduleName + "] loaded");
            }
            else {
                LOGGER.debug("ModuleDescriptor for module [" + moduleName + "] cannot be loaded");
            }
        }

        result.add(new ModuleDescriptor());
        return result;
    }


    /**
     * Returns a list of all scanned and registered classes in the application classpath.
     */
    public Set<Class<? extends PersistentObject>> getAllPersistentClasses() {
        final Set<Class<? extends PersistentObject>> result = new LinkedHashSet();
        

//        for ( final AbstractModuleDescriptor descriptor : getAllModuleDescritors() ) {
//            descriptor.registerPersistentClasses(result);
//        }

        loadPersitentBeans(result, STANDARD_BASE_PACKAGE);
        loadPersitentBeans(result, "ar.com.syr.transportes");

//        final List<String> modules = FlexyConfiguration.getModules();
//        final String packagePrefix = FlexyConfiguration.getPackagePrefix();

//        for (String moduleName : modules) {
//        	if ( !(packagePrefix + "." + moduleName).equals(STANDARD_BASE_PACKAGE) ) {
//        		loadPersitentBeans(result, packagePrefix + "." + moduleName);
//        	}
//		}

        return result;
    }


    public void loadPersitentBeans(final Set<Class<? extends PersistentObject>> result, final String packageName) {
        final AnnotationClassPredicate persistenceFilter = new AnnotationClassPredicate(MappedSuperclass.class, Entity.class, Characteristic.class);
        for ( final Class _class : inferencer.getClassesFor(this.encodePackage(packageName) + ".*bean.*", persistenceFilter) ) {
            result.add(_class);
        }
    }


    /**
     * Returns a list of all scanned and registered persistence classes that are also lucene classes.
//     */
//    public Collection<Class<? extends PersistentObject>> getAllLuceneIndexedClasses() {
//        return CollectionUtils.select(this.getAllPersistentClasses(), new AnnotationClassPredicate(Indexed.class));
//    }


    public List<Injector> getAllInjectors() {
        final List<Injector> result = new ArrayList();
        for ( final AbstractModuleDescriptor descriptor : this.getAllModuleDescritors() ) {
            descriptor.registerInjectors(result);
        }
        return result;
    }


    public List<Serializer> getFlexSerializers() {
        final List<Serializer> result = new ArrayList();
        for ( final AbstractModuleDescriptor descriptor : this.getAllModuleDescritors() ) {
            descriptor.registerFlexSerializers(result);
        }
        return result;
    }


//    public List<Synchronizer> getBusinessObjectSynchronizers() {
//        final List<Synchronizer> result = new ArrayList();
//        for ( final AbstractModuleDescriptor descriptor : this.getAllModuleDescritors() ) {
//            descriptor.registerBusinessObjectSynchronizers(result);
//        }
//        return result;
//    }


//    public List<AdministrativeAction> getAdministrativeActions() {
//        final List<AdministrativeAction> result = new ArrayList<AdministrativeAction>();
//        for ( final AbstractModuleDescriptor descriptor : this.getAllModuleDescritors() ) {
//            descriptor.registerAdministrativeAction(result);
//        }
//        return result;
//    }


    public List<FilterEnabler> getFilterEnablers() {
    	final List<FilterEnabler> result = new ArrayList<FilterEnabler>();
    	for ( final AbstractModuleDescriptor descriptor : this.getAllModuleDescritors() ) {
    		descriptor.registerFilterEnablers(result);
    	}
    	return result;
    }
    
//    
//    public Collection<Class> getAllServices() {
//    	LOGGER.debug("getAllServices()");
//        final AnnotationClassPredicate serviceFilter = new AnnotationClassPredicate(Service.class);
//        final List<Class> serviceClasses = inferencer.getClassesFor(encodePackage(STANDARD_BASE_PACKAGE) + "\\..*\\.service\\..*", serviceFilter);
//
//        final List<String> modules = FlexyConfiguration.getModules();
//        final String packagePrefix = FlexyConfiguration.getPackagePrefix();
//
//        for (String moduleName : modules) {
//        	if ( !(packagePrefix + "." + moduleName).equals(STANDARD_BASE_PACKAGE) ) {
//                serviceClasses.addAll(inferencer.getClassesFor(encodePackage(packagePrefix + "." + moduleName) + "\\..*\\.service\\..*", serviceFilter));
//        	}
//		}
//        
//        return serviceClasses;
//    }


    public Collection<Class> getWebserviceTypes(final Class typeClass) {
    	return inferencer.getClassesFor(encodePackage(STANDARD_BASE_PACKAGE) + "\\..*\\.service\\..*", new SuperclassPredicate(typeClass));
    }
}
