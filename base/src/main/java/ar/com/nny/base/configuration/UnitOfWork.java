package ar.com.nny.base.configuration;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.Search;

/**
 * Just a holder for the session/transaction pair
 */
public class UnitOfWork {

	private Session session;
	private Transaction transaction;
	private TransactionManager txManager;
	private final AsynchronicRunner asyncRunner = new AsynchronicRunner();

	private String sessionName;


	public UnitOfWork(final Session session, final TransactionManager txManager) {
		this.session = session != null ? Search.createFullTextSession(session) : null;
		this.txManager = txManager;
		this.transaction = txManager.openTransaction(this.session);
	}


	public String getSessionName() {
		return sessionName;
	}


	public void setSessionName(final String sessionName) {
		this.sessionName = sessionName;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(final Session session) {
		this.session = session;
	}


	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(final Transaction transaction) {
		this.transaction = transaction;
	}


	public AsynchronicRunner getAsyncRunner() {
		return asyncRunner;
	}


	public void setTxManager(final TransactionManager txManager) {
		this.txManager = txManager;
	}


	public void commit() {
		txManager.commitTransaction(this.transaction);
	}


	public void rollback() {
		if ( this.transaction != null ) {
			txManager.rollbackTransaction(this.transaction);
		}
	}


	public void close() {
		if ( this.session != null ) {
			this.session.close();
		}
	}


	public UnitOfWork openNext() {
		return new UnitOfWork(this.session.getSessionFactory().openSession(), TransactionManager.STANDARD);
	}


	public boolean hasSessionName(final String name) {
		return (name == null && this.sessionName == null) || (name != null && name.equals(this.sessionName));
	}

}