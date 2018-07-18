package by.mytaxi.demo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TaxiFirstConnection {

    private static Logger log = LogManager.getLogger(TaxiFirstConnection.class);

    private static final String URL = "jdbc:mysql://localhost:3306/mytaxi";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "pass";

    private static final String QUERY = "CREATE TABLE IF NOT EXISTS users(id " +
            "INT(64) NOT NULL AUTO_INCREMENT, name VARCHAR(10), PRIMARY KEY(id))";
    private static final String DROP_USERS_TABLE = "DROP TABLE users";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;


        try {
            connection = getConnection();
            log.info("Database catalog is " + connection.getCatalog());

            statement = connection.createStatement();
            statement.execute(DROP_USERS_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static Connection getConnection() throws SQLException {
        Properties info = new Properties();
        info.setProperty("user", USER_NAME);
        info.setProperty("password", PASSWORD);
        info.setProperty("useSSL", "true");
        info.setProperty("serverTimezone", "UTC");
        return DriverManager.getConnection(URL, info);
    }

}
