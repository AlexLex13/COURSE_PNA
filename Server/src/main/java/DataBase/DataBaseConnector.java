package DataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnector {
    private Connection connection = null;
    private Statement statement = null;

    public void createDBConnection() {
        try {
            var prop = getPropertiesFromConfig();
            var connectionString = prop.getProperty("connectionString");
            var username = prop.getProperty("user");
            var password = prop.getProperty("password");

            this.connection = DriverManager.getConnection(connectionString, username, password);
            this.statement = connection.createStatement();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
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

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
