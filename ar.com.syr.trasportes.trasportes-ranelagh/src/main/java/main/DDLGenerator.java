package main;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;

public class DDLGenerator implements Runnable {

    @Override
    public void run() {
        try {
            ar.com.nny.base.generator.DDLGenerator.main();
        } catch (Exception e) {
        }

    }

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        Logger.getAnonymousLogger().setLevel(Level.INFO);
        DDLGenerator main = new DDLGenerator();
        EventQueue.invokeLater(main);
    }
}
