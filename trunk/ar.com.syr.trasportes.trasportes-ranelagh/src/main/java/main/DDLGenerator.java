package main;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;

public class DDLGenerator implements Runnable {

    @Override
    public void run() {
          ar.com.nny.base.generator.DDLGenerator.main();
    }

    public static void main(final String[] args) {
        DDLGenerator main = new DDLGenerator();
        EventQueue.invokeLater(main);
    }
}
