package exam.program;

import exam.DBManager;
import exam.query.Category;
import exam.query.Measure;
import exam.query.PivotReportQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gevorg on 3/3/14.
 */
public class PivotReportQueryProgram extends Program {
    protected Category tableCategory;
    protected Category columnCategory;
    protected Category rowCategory;
    protected Measure measure;

    public PivotReportQueryProgram(String[] args) {
        try {
            if ( args.length != 5 ) { // Length check.
                throw new IllegalArgumentException("Invalid number of arguments!");
            }

            // Validate categories.
            tableCategory = Category.validate(args[1]);
            columnCategory = Category.validate(args[2]);
            rowCategory = Category.validate(args[3]);

            // Check for repeating categories.
            if ( args[1].equals(args[2]) || args[1].equals(args[3]) || args[2].equals(args[3]) ) {
                throw new IllegalArgumentException("Categories should not repeat!");
            }

            // Validate measure.
            measure = Measure.validate(args[4]);
        } catch (RuntimeException e) {
            printHelp();
            throw e;
        }

        System.out.println("Program initialized with arguments [Table category: " + args[1] +
                ", Column category: " + args[2] + ", Row category: " + args[3] + ", Measure: " + args[4] + "].\n");
    }

    protected void printHelp() {
        System.out.println("\nFollowing arguments are required:\n");
        System.out.println("<table category> <column category> <row category> <measure>\n");
        System.out.println("* category arguments must be: \"Product\", \"Client\", \"Year\", \"Product Type\" or \"Country\"");
        System.out.println("* measure argument must be: \"Count\" or \"Total Cost\"\n");
    }

    private void printTableRow(String tableCategory, List<String> rowCategories) {
        printSeparator(rowCategories.size() + 1);
        printCell(tableCategory);

        for (String rowCategory : rowCategories) {
            printCell(rowCategory);
        }
        printSeparator(rowCategories.size() + 1);
    }

    public void printResultSet(ResultSet resultSet) throws SQLException {
        String tableCategory = "";
        String columnCategory = "";

        // List for row categories.
        List<String> rowCategories = new ArrayList<String>();
        boolean rowsExtracted = false;

        while (resultSet.next()) {
            if (tableCategory.equals(resultSet.getString(1))) {
                if (columnCategory.equals(resultSet.getString(2))) {
                    if (!rowsExtracted) {
                        rowCategories.add(resultSet.getString(3));
                    }

                    printCell(resultSet.getString(4));
                } else {
                    System.out.println();
                    rowsExtracted = true;
                    columnCategory = resultSet.getString(2);

                    printCell(columnCategory);
                    printCell(resultSet.getString(4));
                }
            } else {
                if ( !tableCategory.isEmpty() ) {
                    if (!rowsExtracted) {
                        rowsExtracted = true;
                    }

                    printTableRow(tableCategory, rowCategories);
                    System.out.println();
                    System.out.println();
                }

                tableCategory = resultSet.getString(1);
                columnCategory = resultSet.getString(2);
                if (!rowsExtracted) {
                    rowCategories.add(resultSet.getString(3));
                }

                printCell(columnCategory);
                printCell(resultSet.getString(4));
            }
        }

        printTableRow(tableCategory, rowCategories);
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
            printResultSet(resultSet);
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
