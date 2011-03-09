package ar.com.nny.base.configuration;

import java.util.Collection;
import java.util.List;

import ar.com.nny.base.utils.PersistentObject;

public class ModuleDescriptor extends AbstractModuleDescriptor {

	@Override
	public void registerPersistentClasses(final Collection<Class<? extends PersistentObject>> list) {
	}


	@Override
	public void registerInjectors(final List<Injector> list) {
//		list.add(new DAOInjector());
//		list.add(new ServiceInjector());
//		list.add(new ConfigurationInjector());
	}


	@Override
	public void registerFlexSerializers(final List<Serializer> list) {
	}

//
//	@Override
//	public void registerBusinessObjectSynchronizers(final List<Synchronizer> list) {
//	}
//
//
//	@Override
//	public void registerAdministrativeAction(final List<AdministrativeAction> list) {
//	}

}
