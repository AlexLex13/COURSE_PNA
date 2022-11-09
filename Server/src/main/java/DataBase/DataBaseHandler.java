package DataBase;

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
        System.out.println("Opened database successfully");
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

    public static void main(String[] args) throws SQLException {
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDBConnection().prepareStatement("SELECT * FROM \"Admin\" ORDER BY id ASC ");
            resSet = prSt.executeQuery();
            resSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(resSet.getString("password"));
    }
}
