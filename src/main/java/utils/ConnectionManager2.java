package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager2 {
    private static final String DB_URL = PropertiesReader.getProperty("url");
    private static final String DB_USER = PropertiesReader.getProperty("user");
    private static final String DB_PASSWORD = PropertiesReader.getProperty("password");
    private static final String JDBC_DRIVER = PropertiesReader.getProperty("jdbcDriver");

    private ConnectionManager2() {
    }

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection get() {

        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
