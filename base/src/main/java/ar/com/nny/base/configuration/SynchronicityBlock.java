package ar.com.nny.base.configuration;


public class SynchronicityBlock extends AbstractBlockDecorator {

    public SynchronicityBlock(final CodeBlock block) {
        super(block);
    }


    public Object execute() throws Exception {
        final Synchronicity metadata = this.getMetadata(Synchronicity.class);

        SynchronicityType synch = SynchronicityType.SYNCHRONOUS;
        if ( metadata != null ) {
            synch = metadata.value();
        }

        return synch.execute(this.block);
    }

}
