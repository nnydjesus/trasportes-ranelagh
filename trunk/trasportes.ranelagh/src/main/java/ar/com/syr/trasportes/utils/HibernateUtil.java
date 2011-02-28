package ar.com.syr.trasportes.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Clase de utilidad para obtener la sesion de hibernate.
 *
 * @author documentacion hibernate
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Si no ponemos fichero, intenta cargar "/hibernate.cfg.xml" en el
            // raiz
            sessionFactory = new AnnotationConfiguration().configure(
                    new File("hibernate.cfg.xml")).buildSessionFactory();
          BasicConfigurator.configure();
          Logger.getAnonymousLogger().setLevel(Level.INFO);
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}

