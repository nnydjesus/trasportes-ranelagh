package main;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import ar.com.nny.base.generator.DDLGenerator;
import ar.com.syr.transportes.ui.LoginUser;

public class Main implements Runnable {

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            new LoginUser();
        } catch (Exception e) {
        }

    }

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        Logger.getAnonymousLogger().setLevel(Level.INFO);
        // JFrame.setDefaultLookAndFeelDecorated(true);
        // TransportesRanelagh reproductor = new TransportesRanelagh(new
        // PreInicio());
//         DDLGenerator.main();
        Main main = new Main();
        EventQueue.invokeLater(main);
        // new Login();
        // HibernateUtil.getSession();
    }

}
