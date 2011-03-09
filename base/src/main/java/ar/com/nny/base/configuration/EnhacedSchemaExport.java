package ar.com.nny.base.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import ar.com.nny.base.exception.NonBusinessException;
import ar.com.nny.base.utils.ReflectionUtils;

public class EnhacedSchemaExport extends SchemaExport {

    private final Configuration cfg;


    public EnhacedSchemaExport(Configuration cfg) throws HibernateException {
        super(cfg);
        this.cfg = cfg;
    }


    @Override
    public void create(boolean script, boolean export) {
        Class dialect = ReflectionUtils.classForName(this.cfg.getProperty("hibernate.dialect"));
        String url = this.cfg.getProperty("hibernate.connection.url");

        if ( FlexyConfiguration.recreateSchemaOnGeneration() && MySQLDialect.class.isAssignableFrom(dialect) && url != null ) {
            this.recreateSchema(url);
        }

        super.create(script, export);
    }


    private void recreateSchema(String url) {
        PreparedStatement dropStatement = null;
        PreparedStatement executeStatement = null;
        StatelessSession session = null;

        try {
            session = PersistenceManager.getInstance().getDefaultSessionFactory().openStatelessSession();
            Connection connection = session.connection();
            String schema = url.substring(url.lastIndexOf('/') + 1);

            dropStatement = connection.prepareStatement("drop database if exists " + schema);
            dropStatement.execute();
            executeStatement = connection.prepareStatement("create database " + schema);
            executeStatement.execute();

            PersistenceManager.getInstance().destroy();
        }
        catch ( Exception e ) {
            throw new NonBusinessException(e);
        }
        finally {
            try {
                dropStatement.close();
                executeStatement.close();
                session.close();
            }
            catch ( Exception e ) {
            }
        }
    }

}
