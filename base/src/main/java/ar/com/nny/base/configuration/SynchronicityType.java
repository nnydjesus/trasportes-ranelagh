package ar.com.nny.base.configuration;


public enum SynchronicityType {

    SYNCHRONOUS {
        @Override
		public Object execute(final CodeBlock block) throws Exception {
			return block.execute();
        }
    };
//    ASYNCHRONOUS {
//        @Override
//		public Object execute(final CodeBlock block) throws Exception {
//            final Runnable runnable = new RunnableBlock(block);
//            ContextSessionFacade.getCurrentAsynchronicRunner().runAsynchronic(runnable);
//            return null;
//        }
//    },
//    ASYNCHRONOUS_DELAYED {
//        @Override
//		public Object execute(final CodeBlock block) throws Exception {
//            final Runnable runnable = new RunnableBlock(block);
//            ContextSessionFacade.getCurrentAsynchronicRunner().delay(runnable);
//            return null;
//        }
//    };


    public abstract Object execute(CodeBlock block) throws Exception;
}
