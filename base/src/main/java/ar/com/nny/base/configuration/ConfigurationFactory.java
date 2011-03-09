package ar.com.nny.base.configuration;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.engine.FilterDefinition;

import ar.com.nny.base.utils.PersistentObject;
import edu.emory.mathcs.backport.java.util.Collections;

public class ConfigurationFactory {

    private static Log LOGGER = LogFactory.getLog(ConfigurationFactory.class);


    public FlexyAnnotationConfiguration buildConfiguration(final String name, final Properties properties) {
    	LOGGER.debug("Building hibernate configuration...");
    	
        final FlexyAnnotationConfiguration configuration = new FlexyAnnotationConfiguration();
        configuration.setNamingStrategy(buildNamingStrategy(properties));

        properties.setProperty("hibernate.search.default.indexBase", FlexyConfiguration.getWorkingDirectoryFile().getAbsolutePath() + File.separator + "lucene-db");
        configuration.setProperties(properties);

        final FilterDefinition filter = new FilterDefinition(SoftDeleteManager.SOFT_DELETE_FILTER, null, Collections.singletonMap(SoftDeleteManager.DELETED_FLAG, Hibernate.BOOLEAN));
        configuration.addFilterDefinition(filter);

        final Collection<Class<? extends PersistentObject>> classes = ApplicationRegistryReader.getInstance().getAllPersistentClasses();
        for (final Class<? extends PersistentObject> persistentClass : classes) {

            final AlternateSessionFactory annotation = persistentClass.getAnnotation(AlternateSessionFactory.class);
            if ((annotation == null && name == null) || (annotation != null && name != null && annotation.value().equals(name))) {
                LOGGER.debug("Registering persistent class " + persistentClass.getName());

                if (persistentClass.getAnnotation(Characteristic.class) != null) {
                    configuration.addCharacteristicClass(persistentClass);
                } else {
                    configuration.addAnnotatedClass(persistentClass);
                }

            }
        }

        //configuration.setInterceptor(new FlexyHibernateInterceptor());
        //configuration.setListener("load-collection", new FlexyInitializeCollectionEventListener());
        return configuration;
    }


    private NamingStrategy buildNamingStrategy(final Properties properties) {
    	LOGGER.debug("Building naming strategy...");

    	NamingStrategy strategy = new ImprovedNamingStrategy();
        final String value = properties.getProperty("hibernate.namingStrategy");

        try {
            if (value != null && value.trim().length() > 0) {
                strategy = (NamingStrategy) Class.forName(value.trim()).newInstance();
            }
        }
        catch (final Exception e) {
            LOGGER.warn("Invalid Hibernate Naming Strategy: [" + value + "]: " + e.getMessage(), e);
        }
        return strategy;
    }

}
