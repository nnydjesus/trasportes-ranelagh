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
    
    private static TransportesRanelagh app;
    
    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            app = new TransportesRanelagh();
            getApp().setVisible(true);
//            new LoginUser();
        } catch (Exception e) {
        }

    }

    public static void main(final String[] args) {
        // JFrame.setDefaultLookAndFeelDecorated(true);
        // TransportesRanelagh reproductor = new TransportesRanelagh(new
        // PreInicio());
//         DDLGenerator.main();
        Main main = new Main();
        EventQueue.invokeLater(main);
        // new Login();
        // HibernateUtil.getSession();
    }


    public static  TransportesRanelagh getApp() {
        return app;
    }

}
