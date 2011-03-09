package ar.com.nny.base.configuration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import ar.com.nny.base.initialData.DataGenerator;
import ar.com.nny.base.utils.HibernateUtil;
import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;

@Service(denyable=true)
public class DatabaseInitializerService {

    private static Log LOGGER = LogFactory.getLog(DatabaseInitializerService.class);

    private LuceneIndexManagingService luceneIMService;


    @Transaction(TransactionType.REQUIRES_NEW)
    public void initializeSchema() {
        LOGGER.debug("Creating database schema from class mappings...");
//        luceneIMService.clearAll();
//        Configuration configure = new Configuration().configure( new File("hibernate.cfg.xml"));
//        Properties extraProperties = new Properties();
//        extraProperties.setProperty("hbm2ddl.auto", "true");
//		configure.addProperties(extraProperties);
//		SchemaExport exporter = new SchemaExport(configure);
        final SchemaExport exporter = new EnhacedSchemaExport(PersistenceManager.getInstance().getConfiguration());
//        exporter.drop(true, true);
        exporter.create(true, true);
    }


    @Transaction(TransactionType.REQUIRES_NEW)
    public void initializeData(final boolean dummyMode) {
//        SecurityHelper.overrideSecurity();

        LOGGER.debug("Creating initial data...");
        System.out.println("Initializing Data *****************************************");
        final Collection<Class<? extends PersistentObject>> persistentClasses = ApplicationRegistryReader.getInstance().getAllPersistentClasses();

        final DependencyManager<InitialDataGenerator> _dependencyManager = new DependencyManager<InitialDataGenerator>();
        final List<CharacteristicGenerator> _caracteristicaGenerators = new ArrayList<CharacteristicGenerator>();

        for ( final Class<? extends PersistentObject> persistentClass : persistentClasses ) {
            final DataGenerator generatorAnnotation = persistentClass.getAnnotation(DataGenerator.class);
            if ( generatorAnnotation != null ) {
                _dependencyManager.add(ReflectionUtils.instanciate(generatorAnnotation.value()));
            }

            if ( persistentClass.getAnnotation(Characteristic.class) != null ) {
                _caracteristicaGenerators.add(new CharacteristicGenerator(persistentClass));
            }
        }

        for ( final CharacteristicGenerator _initialDataGenerator : _caracteristicaGenerators ) {
            this.runGenerator(dummyMode, _initialDataGenerator);
        }
        for ( final InitialDataGenerator _initialDataGenerator : _dependencyManager ) {
            this.runGenerator(dummyMode, _initialDataGenerator);
        }

        System.out.println("Initialization Done ***************************************");

        System.out.println("Optimizing Indexes ****************************************");
        luceneIMService.optimizeAll();
        System.out.println("Optimization Done *****************************************");
    }


    @Transaction(TransactionType.REQUIRES_NEW)
    public void runGenerator(final boolean dummyMode, final InitialDataGenerator generator) {
        generator.run(dummyMode);
    }


    @Transaction(TransactionType.REQUIRES_NEW)
    private void runInitialStatements(final String sqlFilePath) {
        Statement stmt;

        final File sqlFiles = new File(sqlFilePath);
        final File[] files = sqlFiles.listFiles();
        if ( files != null ) {
            try {
                final Connection conn = PersistenceManager.getInstance().getCurrentSession().connection();
                for ( int i = 0; i < files.length; i++ ) {
                    if ( files[i].isFile() ) {
                        String sql = FileUtils.readFileToString(files[i], "UTF-8");
                        sql = sql.replaceAll("\\n", "");
                        sql = sql.replaceAll("\\t", "");
                        sql = sql.replaceAll("\\r", "");
                        stmt = conn.createStatement();
                        final String[] sqlBatch = sql.split(";");
                        for ( int j = 0; j < sqlBatch.length; j++ ) {
                            stmt.addBatch(sqlBatch[j]);
                        }
                        stmt.executeBatch();
                    }
                }
            }
            catch ( final IOException e ) {
                System.out.println("Can not read SQLfile");
            }
            catch ( final HibernateException e ) {
                System.out.println("Can not get session");
            }
            catch ( final SQLException e ) {
                System.out.println("Can not execute query: " + e.getMessage());
            }
        }
    }


    public void runInitialPostStatements() {
        System.out.println("------RUNNING INITIAL POST STATMENTS------");
        final String sqlFilePath = JFigConfiguration.getInstance().getString("initialStatements", "postPath");
        this.runInitialStatements(sqlFilePath);
        System.out.println("--------END INITIAL POST STATMENTS--------");
    }


    public void runInitialPrevStatements() {
        System.out.println("------RUNNIGN INITIAL PREV STATMENTS------");
        final String sqlFilePath = JFigConfiguration.getInstance().getString("initialStatements", "prevPath");
        this.runInitialStatements(sqlFilePath);
        System.out.println("--------END INITIAL PREV STATMENTS--------");
    }
    
    public static void main(String[] args) {
		SchemaExport exporter = new SchemaExport(HibernateUtil.getConfiguration());
        exporter.drop(true, true);
        exporter.create(true, true);
	}

}
