package exam;


import exam.program.Program;
import exam.program.ProgramFactory;

/**
 * Created by gevorg on 3/3/14.
 */
public class PivotReportsAPI {
    public static void main(String[] args) {
        try {
            Program program = ProgramFactory.createProgram(args);
            program.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
