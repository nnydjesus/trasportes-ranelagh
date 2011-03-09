package ar.com.nny.base.configuration;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@SuppressWarnings("unchecked")
public class FlexyConfiguration {
    //This class must not reference LOGGER. Just because, trust me.
    //Okey okey, FlexyDailyRollingFileAppender use this and we should avoid circular dependencies.

    public static void setValue(final String section, final String parameter, final String value) {
        JFigConfiguration.getInstance().setValue(section, parameter, value);
    }


    public static Properties getHibernateProperties() {
        return JFigConfiguration.getInstance().getSectionAsProperties("hibernate");
    }
    
    public static String getPackagePrefix() {
        return JFigConfiguration.getInstance().getString("modules", "package.prefix");
    }
    
    public static List<String> getModules() {
        final String list = JFigConfiguration.getInstance().getString("modules", "list");
        final String[] splittedList = list.split(",");
        return Arrays.asList(splittedList);
    }
    
    @Deprecated
    public static String getWorkingDirectory() {
        return getWorkingDirectoryFile().getAbsolutePath();
    }

	public static Map<String, Properties> getAlternateHibernateProperties() {
        final Map<String, Properties> result = new HashMap();

        final String string = JFigConfiguration.getInstance().getString("hibernate", "alternateSessionFactories");
        final String[] alternateSessionFactories = string.split(",");
        for (final String alternate : alternateSessionFactories) {
            if (!alternate.equals("")) {
                final Properties properties = JFigConfiguration.getInstance().getSectionAsProperties("hibernate." + alternate);
                result.put(alternate, properties);
            }
        }
        return result;
    }


    public static boolean recreateSchemaOnGeneration() {
        return JFigConfiguration.getInstance().getBoolean("hibernate", "ddl.recreateSchema");
    }
    public static File getWorkingDirectoryFile() {
//        final File _file = new File(JFigConfiguration.getInstance().getString("enviroment", "working.directory"));
//        _file.mkdirs();
//        return _file;
    	return new File("");
    }

    

}
