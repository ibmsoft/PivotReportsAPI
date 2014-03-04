package exam.program;

import exam.DBManager;
import exam.query.PivotReportQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by gevorg on 3/3/14.
 */
public class PivotReportIteratorProgram extends PivotReportQueryProgram {
    public PivotReportIteratorProgram(String[] args) {
        super(args);
    }

    private void iterate(ResultSet resultSet) throws SQLException, IOException {
        System.out.println("Push enter to iterate, type '+' for full data or type 'QUIT' to exit");

        // Open up standard input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String token = br.readLine();

            if ("".equals(token) || "+".equals(token)) {
                if (resultSet.next()) {
                    if ("+".equals(token)) { // Additional data.
                        System.out.print(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " +
                                resultSet.getString(3) + ", ");
                    }
                    // Data.
                    System.out.println(resultSet.getString(4));
                } else {
                    break;
                }
            } else if ("quit".equals(token.toLowerCase())) {
                break;
            }
        }

        System.out.println("DONE");
    }

    @Override
    public void start() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            PivotReportQuery reportQuery = new PivotReportQuery(tableCategory, columnCategory, rowCategory, measure);
            System.out.println("SQL> " + reportQuery.getQueryReportSQL() + "\n\n");

            resultSet = statement.executeQuery(reportQuery.getQueryReportSQL());
            iterate(resultSet);
        } catch (Exception e) {
            printHelp();
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception ignored) {}
        }
    }
}
