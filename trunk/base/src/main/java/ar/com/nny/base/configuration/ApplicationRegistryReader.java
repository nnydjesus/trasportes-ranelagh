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
import org.hibernate.search.annotations.Indexed;

import ar.com.nny.base.generator.AnnotationClassPredicate;
import ar.com.nny.base.persistence.PersistenceManager;
import ar.com.nny.base.persistence.PersistentObject;
import ar.com.nny.base.utils.HibernateUtil;
import ar.com.nny.base.utils.ReflectionUtils;
import ar.com.nny.base.utils.TypeInferencer;

/**
 * Read the moduleDescriptors for each module configured in the application and
 * returns information about all of them.
 *
 */
public class ApplicationRegistryReader {

    private static final Log LOGGER = LogFactory.getLog(ApplicationRegistryReader.class);
    public static final String STANDARD_BASE_PACKAGE = "ar.com.nny.base";
    private String specialPackage = PersistenceManager.getStaticConfiguration().getProperty("package");


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
    public Set<Class<? extends PersistentObject>> getAllPersistentClasses() {
        final Set<Class<? extends PersistentObject>> result = new LinkedHashSet();
        

//        for ( final AbstractModuleDescriptor descriptor : getAllModuleDescritors() ) {
//            descriptor.registerPersistentClasses(result);
//        }
        loadPersitentBeans(result, STANDARD_BASE_PACKAGE);
        if(specialPackage != null)
        	loadPersitentBeans(result,	specialPackage);

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
        final AnnotationClassPredicate persistenceFilter = new AnnotationClassPredicate(MappedSuperclass.class, Entity.class);
        for ( final Class _class : inferencer.getClassesFor(this.encodePackage(packageName) + ".*bean.*", persistenceFilter) ) {
            result.add(_class);
        }
    }


    /**
     * Returns a list of all scanned and registered persistence classes that are also lucene classes.
//     */
    public Collection<Class<? extends PersistentObject>> getAllLuceneIndexedClasses() {
        return CollectionUtils.select(this.getAllPersistentClasses(), new AnnotationClassPredicate(Indexed.class));
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
