package ar.com.nny.base.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import ar.com.nny.base.configuration.ApplicationRegistryReader;
import ar.com.nny.base.configuration.Characteristic;
import ar.com.nny.base.configuration.CharacteristicGenerator;
import ar.com.nny.base.configuration.DependencyManager;
import ar.com.nny.base.configuration.InitialDataGenerator;
import ar.com.nny.base.configuration.Transaction;
import ar.com.nny.base.configuration.TransactionType;
import ar.com.nny.base.initialData.DataGenerator;
import ar.com.nny.base.utils.HibernateUtil;
import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;


@SuppressWarnings("unchecked")
public class DDLGenerator {

	private static Log LOGGER = LogFactory.getLog(DDLGenerator.class);

	
	private void initializeSchema(){
		SchemaExport exporter = new SchemaExport(HibernateUtil.getConfiguration());
		LOGGER.debug("Eliminando la tabla...");
        exporter.drop(true, true);
        LOGGER.debug("Creando el schema...");
        exporter.create(true, true);
        LOGGER.debug("End initialize schema");
	}
	
	public void initializeData(final boolean dummyMode) {
//       SecurityHelper.overrideSecurity();

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
       System.out.println("Optimization Done *****************************************");
   }
	 
    @Transaction(TransactionType.REQUIRES_NEW)
    public void runGenerator(final boolean dummyMode, final InitialDataGenerator generator) {
        generator.run(dummyMode);
    }
	
	 public static void main(final String[] args) {
//	        final DatabaseInitializerService service = ServiceLocator.locate(DatabaseInitializerService.class);

	        //run initial post statements
	        //************************************************//
//	        service.runInitialPrevStatements();
	        //************************************************//
		 	DDLGenerator ddlGenerator = new DDLGenerator();
	        //build up dataBase with entities and generators
	        //************************************************//
	        final boolean dummyMode = true;
	        ddlGenerator.initializeSchema();
	        ddlGenerator.initializeData(dummyMode);
	        //************************************************//

	        //run initial post statements
	        //************************************************//
//	        service.runInitialPostStatements();
	        //************************************************//
	    }

}
