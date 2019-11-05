package db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
    private static DaoFactory instance = null;
    private final Properties properties;

    private DaoFactory() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("main/resources/settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    private ConnectionFactory getConnectionFactory() {
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        String url = properties.getProperty("connection.url");
        String driver = properties.getProperty("connection.driver");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }

    public UserDao getUserDao() {
        UserDao result = null;
        try {
            Class clazz = Class.forName(properties.getProperty("dao.knure.ctde.usermanagement.db.UserDao"));
            result = (UserDao) clazz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
