package ar.com.nny.base.generator;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import ar.com.nny.base.configuration.ApplicationRegistryReader;
import ar.com.nny.base.generator.annotations.DataGenerator;
import ar.com.nny.base.persistence.PersistenceManager;
import ar.com.nny.base.persistence.PersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;

@SuppressWarnings("rawtypes")
public class DDLGenerator {

    private static Log LOGGER = LogFactory.getLog(DDLGenerator.class);

    private void initializeSchema() {
        SchemaExport exporter = new SchemaExport(PersistenceManager.getInstance().getConfiguration());
        PersistenceManager.getInstance().clearCurrentSession();
        LOGGER.debug("Eliminando la tabla...");
        exporter.drop(true, true);
        LOGGER.debug("Creando el schema...");
        exporter.create(true, true);
        LOGGER.debug("End initialize schema");
    }

    public void initializeData(final boolean dummyMode) {
        // SecurityHelper.overrideSecurity();

        LOGGER.debug("Creating initial data...");
        System.out.println("Initializing Data *****************************************");
        final Collection<Class<? extends PersistentObject>> persistentClasses = ApplicationRegistryReader.getInstance()
                .getAllPersistentClasses();

        final DependencyManager<InitialDataGenerator> _dependencyManager = new DependencyManager<InitialDataGenerator>();
        // final List<CharacteristicGenerator> _caracteristicaGenerators = new
        // ArrayList<CharacteristicGenerator>();

        for (final Class<? extends PersistentObject> persistentClass : persistentClasses) {
            final DataGenerator generatorAnnotation = persistentClass.getAnnotation(DataGenerator.class);
            if (generatorAnnotation != null) {
                _dependencyManager.add(ReflectionUtils.instanciate(generatorAnnotation.value()));
            }

        }

        for (final InitialDataGenerator _initialDataGenerator : _dependencyManager) {
            this.runGenerator(dummyMode, _initialDataGenerator);
        }

        System.out.println("Initialization Done ***************************************");

        System.out.println("Optimizing Indexes ****************************************");
        System.out.println("Optimization Done *****************************************");
    }

    public void runGenerator(final boolean dummyMode, final InitialDataGenerator generator) {
        generator.run(dummyMode);
    }

    public static void main() {
        // final DatabaseInitializerService service =
        // ServiceLocator.locate(DatabaseInitializerService.class);

        // run initial post statements
        // ************************************************//
        // service.runInitialPrevStatements();
        // ************************************************//
        DDLGenerator ddlGenerator = new DDLGenerator();
        // build up dataBase with entities and generators
        // ************************************************//
        final boolean dummyMode = true;
        ddlGenerator.initializeSchema();
        ddlGenerator.initializeData(dummyMode);
        // ************************************************//

        // run initial post statements
        // ************************************************//
        // service.runInitialPostStatements();
        // ************************************************//
    }

}
