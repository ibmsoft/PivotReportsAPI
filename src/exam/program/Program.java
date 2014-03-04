package exam.program;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by gevorg on 3/3/14.
 */
public abstract class Program {
    private static final int PRINT_SIZE = 20;

    protected void printCell(String data) {
        System.out.print(formatPrintable(data) + " | ");
    }

    protected String formatPrintable(String data) {
        int length = data.length();

        if (length < PRINT_SIZE) {
            return data + StringUtils.repeat(' ', PRINT_SIZE - length);
        } else {
            return data.substring(0, PRINT_SIZE);
        }
    }

    protected void printSeparator(int columnCount) {
        int length = columnCount * (PRINT_SIZE + 3);
        System.out.println("\n" + StringUtils.repeat('-', length));
    }

    public abstract void start();
}
