package ar.com.nny.base.configuration;


public enum TransactionType {

    /**
     * Method requires to run inside a transaction (could join to an old one).
     */
    REQUIRES {
        @Override
		public Object executeWithTransactionPresent(final CodeBlock block) throws Exception {
			return block.execute();
        }

        @Override
		public Object executeWithoutTransactionPresent(final CodeBlock block) throws Exception {
            return PersistenceManager.getInstance().executeTransactionalContext(block);
        }
    },

    /**
     * Method requires to run in a new transaction. Old transaction, if exists,
     * should be suspended.
     */
    REQUIRES_NEW {
        @Override
		public Object executeWithTransactionPresent(final CodeBlock block) throws Exception {
            return PersistenceManager.getInstance().executeTransactionalContext(block);
        }

        @Override
		public Object executeWithoutTransactionPresent(final CodeBlock block) throws Exception {
            return PersistenceManager.getInstance().executeTransactionalContext(block);
        }
    },
    
    /**
     * Method supports to run in a transaction. If no transaction exists it does not
     * worry about opening one.
     */
    SUPPORTS {
        @Override
		public Object executeWithTransactionPresent(final CodeBlock block) throws Exception {
			return block.execute();
        }

        @Override
		public Object executeWithoutTransactionPresent(final CodeBlock block) throws Exception {
            return PersistenceManager.getInstance().executeTransactionalContext(block, UnitOfWorkFactory.NON_TRANSACTION);
        }
    };


    public abstract Object executeWithTransactionPresent(CodeBlock block) throws Exception;


    public abstract Object executeWithoutTransactionPresent(CodeBlock block) throws Exception;

}
