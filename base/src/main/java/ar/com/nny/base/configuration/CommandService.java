package ar.com.nny.base.configuration;


@Service(denyable=true)
public class CommandService {

    @Transaction(TransactionType.SUPPORTS)
    public void runRunnable(final Runnable runnable) {
        runnable.run();
    }

}
