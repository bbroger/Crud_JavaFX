package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String url = "jdbc:mysql://localhost/crud";
    private final String user = "root";
    private final String pass = "";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
