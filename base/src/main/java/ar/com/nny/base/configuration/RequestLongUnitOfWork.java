package ar.com.nny.base.configuration;

import org.hibernate.Session;

public class RequestLongUnitOfWork extends UnitOfWork {

    public RequestLongUnitOfWork(Session session) {
        //We start session, not transaction
        super(session, TransactionManager.DUMMY);
    }


    public UnitOfWork openNext() {
        return new RequestPartUnitOfWork(this.getSession(), TransactionManager.STANDARD);
    }


    public static class RequestPartUnitOfWork extends UnitOfWork {

        public RequestPartUnitOfWork(Session session, TransactionManager txManager) {
            //We start transaction, not session
            super(session, txManager);
        }


        @Override
        public void close() {
            //Nothing to do, we don't close session =)
        }
    }
}
