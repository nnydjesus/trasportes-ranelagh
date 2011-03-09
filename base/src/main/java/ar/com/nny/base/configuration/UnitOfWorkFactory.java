package ar.com.nny.base.configuration;

import org.hibernate.SessionFactory;


public enum UnitOfWorkFactory {

    SIMPLE {
        public UnitOfWork build(UnitOfWork parent, SessionFactory sessionFactory) {
            if ( parent != null ) {
                return parent.openNext();
            }
            else {
                return new UnitOfWork(sessionFactory.openSession(), TransactionManager.STANDARD);
            }
        }
    },
    NON_TRANSACTION {
        public UnitOfWork build(UnitOfWork parent, SessionFactory sessionFactory) {
            return new RequestLongUnitOfWork(sessionFactory.openSession());
        }
    };
//    LAZY_NESTED {
//        public UnitOfWork build(UnitOfWork parent, SessionFactory sessionFactory) {
//            return new LazyUnitOfWork(parent);
//        }
//    };


    public abstract UnitOfWork build(UnitOfWork parent, SessionFactory sessionFactory);

}
