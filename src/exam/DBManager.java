package exam;


import java.sql.*;

/**
 * Created by gevorg on 3/3/14.
 */
public class DBManager {
    private static final String DB_CONNECTION = "jdbc:sqlite:data/exam.db";
    private static final DBManager instance = new DBManager();

    public static DBManager getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver!");
        }

        return DriverManager.getConnection(DB_CONNECTION);
    }
}
