package ar.com.nny.base.configuration;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.search.FullTextSession;

import ar.com.nny.base.annotations.persistence.OrderType;
import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.QueryStatement;

/**
 * Servicio de soporte para controles que usan lucene
 *
 * @author hernan.liendo@gmail.com
 */
@Service(denyable=true)
public class LuceneIndexManagingService {

    private static final Log LOGGER = LogFactory.getLog(LuceneIndexManagingService.class);

	public static final String DEFAULT_RT = "default";


	@Transaction(TransactionType.SUPPORTS)
	public void clearAll() {
		LOGGER.debug("clearAll()");
		for ( final Class<? extends PersistentObject> clazz : ApplicationRegistryReader.getInstance().getAllLuceneIndexedClasses() ) {
			clearIndex(clazz);
		}
	}


	@Transaction(TransactionType.REQUIRES_NEW)
	private void clearIndex(final Class<? extends PersistentObject> clazz) {
		final FullTextSession fullTextSession = PersistenceManager.getInstance().getCurrentFullTextSession();
		fullTextSession.purgeAll(clazz);
		fullTextSession.flushToIndexes();
	}


	@Transaction(TransactionType.SUPPORTS)
	public void optimizeAll() {
		LOGGER.debug("optimizeAll()");
		final FullTextSession fullTextSession = PersistenceManager.getInstance().getCurrentFullTextSession();

		fullTextSession.getSearchFactory().optimize();
	}


	@Transaction(TransactionType.SUPPORTS)
	public void rebuildAll() {
		LOGGER.debug("rebuildAll()");
		for ( final Class<? extends PersistentObject> clazz : ApplicationRegistryReader.getInstance().getAllLuceneIndexedClasses() ) {
			this.rebuildIndex(clazz);
		}
	}


	@Transaction(TransactionType.REQUIRES_NEW)
	public void rebuildIndex(final Class _poType) {
		LOGGER.debug("rebuildIndex(" + _poType.getName() + ")");

		final int BATCH_SIZE = 100;
		final FullTextSession fullTextSession = PersistenceManager.getInstance().getCurrentFullTextSession();

		fullTextSession.setFlushMode(FlushMode.MANUAL);
		fullTextSession.setCacheMode(CacheMode.IGNORE);

		final QueryStatement query = new QueryStatement();
		query.addQuery(getReindexQuery(_poType));
		query.addOrderField("id", OrderType.ASC);
		query.setPaginationParameters(0, BATCH_SIZE);

		List<PersistentObject> results = query.find();

		//		List<PersistentObject> results = fullTextSession.createCriteria(_poType)
		//		.setFetchSize(BATCH_SIZE)
		//		.setMaxResults(BATCH_SIZE)
		//		.addOrder(Order.asc("id"))
		//		.list();

		String lastId = null;
		while(!results.isEmpty()) {
			for (final PersistentObject object : results) {
				fullTextSession.index( object ); //index each element
				lastId = object.getId();
			}
			fullTextSession.flushToIndexes(); //apply changes to indexes
			fullTextSession.flush(); //apply changes to indexes
			fullTextSession.clear(); //clear since the queue is processed

			final QueryStatement queryIn = new QueryStatement();
			queryIn.addQuery(getReindexQuery(_poType) + " where this.id > ?");
			queryIn.addParameter(lastId);
			queryIn.addOrderField("id", OrderType.ASC);
			queryIn.setPaginationParameters(0, BATCH_SIZE);

			results = queryIn.find();
		}
	}


	private String getReindexQuery(final Class _poType) {
		if(_poType.isAnnotationPresent(BuildIndexQuery.class)) {
			final BuildIndexQuery annotation = (BuildIndexQuery) _poType.getAnnotation(BuildIndexQuery.class);
			return annotation.value();
		} else {
			return "from " + _poType.getName() + " this";
		}
	}

}
