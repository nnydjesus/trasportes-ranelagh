package main;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import ar.com.syr.transportes.ui.LoginUser;

public class Main implements Runnable {
    
    private static TransportesRanelagh app;
    
    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
//            getApp().setVisible(true);
            new LoginUser();
        } catch (Exception e) {
        }

    }

    public static void main(final String[] args) {
        Main main = new Main();
        EventQueue.invokeLater(main);
    }


    public static  TransportesRanelagh getApp() {
        return app;
    }

    public static TransportesRanelagh createApp() {
        app = new TransportesRanelagh();
        app.setVisible(true);
        return app;
    }

}
