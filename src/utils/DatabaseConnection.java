package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static String connectionURL = "jdbc:postgresql://localhost:5432/movie-streaming-platform";
    static String user = "postgres";
    static String password = "asdf";


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(connectionURL, user, password);
    }


}
