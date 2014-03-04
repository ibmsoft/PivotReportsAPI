package exam.program;

import exam.DBManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by gevorg on 3/3/14.
 */
public class SQLQueryProgram extends Program {
    public SQLQueryProgram() {
        System.out.println("SQL program initialized (to exit write 'QUIT').");
    }

    private void executeSQL(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            printResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception ignored) {}
        }
    }

    public void printResultSet(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        printSeparator(columnCount);

        // Printing columns.
        for (int i = 1; i <= columnCount; ++i) {
            String printable = resultSet.getMetaData().getColumnName(i);
            printCell(printable);
        }

        printSeparator(columnCount);

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; ++i) {
                printCell(resultSet.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void start() {
        // Open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                // Prompt user to enter sql
                System.out.print("SQL> ");

                String sql = br.readLine();
                if ("QUIT".equals(sql.toUpperCase())) {
                    break;
                }

                executeSQL(sql);
            } catch (IOException ioe) {
                throw new RuntimeException("IO error trying to read sql!");
            }
        }
    }
}
