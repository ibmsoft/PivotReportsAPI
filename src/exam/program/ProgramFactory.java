package exam.program;


/**
 * Created by gevorg on 3/3/14.
 */
public class ProgramFactory {
    public static Program createProgram(String[] args) {
        if ( args.length > 0 ) {
            String programModifier = args[0];

            if ("query".equals(programModifier)) {
                return new PivotReportQueryProgram(args);
            } else if ("iterate".equals(programModifier)) {
                return new PivotReportIteratorProgram(args);
            } else if ("sql".equals(programModifier)) {
                return new SQLQueryProgram();
            }
        }

        throw new IllegalArgumentException("The first argument must be one of following: query, sql, iterate!");
    }
}
