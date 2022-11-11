package DataBase;

import Classes.Admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseHandler {

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        
        try {
            var prop = getPropertiesFromConfig();
            var connectionString = prop.getProperty("connectionString");
            var username = prop.getProperty("user");
            var password = prop.getProperty("password");
            dbConnection = DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return dbConnection;
    }

    private static Properties getPropertiesFromConfig() throws IOException {

        var properties = new Properties();
        String propFileName = "Server/src/main/resources/config.properties";
        var inputStream = new FileInputStream(propFileName);
        if (inputStream == null)
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        properties.load(inputStream);
        return properties;
    }
}
