package ar.com.nny.base.configuration;


public class DummyUnitOfWork extends UnitOfWork {

    public DummyUnitOfWork() {
        super(null, TransactionManager.DUMMY);
    }

}
