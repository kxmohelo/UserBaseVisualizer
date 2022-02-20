import org.junit.jupiter.api.*;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserBaseAppTest {
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private String output;

    @BeforeEach
    public void setUpStreams() {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(this.originalOut);
        System.setErr(this.originalErr);
    }

    @Test
    void testHelpOption() {
        this.output = "Usage: ubv [-hV] -t=<targetPath> [-p=<\"dd-MM-yyyy\"> [<\"dd-MM-yyyy\">]]...\n" +
                "User-Base Visualiser\n" +
                "  -p, --period=<\"dd-MM-yyyy\"> [<\"dd-MM-yyyy\">]\n" +
                "                  The date or dates used to filter the data by\n" +
                "  -t, --target=<targetPath>\n" +
                "                  The file whose data to graph\n" +
                "  -h, --help      Show this help message and exit.\n" +
                "  -V, --version   Print version information and exit.";

        new CommandLine(new UserBaseApp()).execute("-h");
        assertEquals(this.output, out.toString().trim());
    }

    @Test
    void testVersionOption() {
         new CommandLine(new UserBaseApp()).execute("-V");
        assertEquals("ubv 1.0", out.toString().trim());
    }

    @Test
    void testTargetOptionValid() {
        this.output = "========================================================================================================================\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\tNumber of Active Users by Date\n" +
                "========================================================================================================================\n" +
                "\n" +
                " 01-01-2022: ▍ 300\n" +
                " 02-01-2022: ▋ 500\n" +
                " 03-01-2022: ▉ 700\n" +
                " 04-01-2022: █ 1300\n" +
                " 05-01-2022: ██ 2000\n" +
                " 06-01-2022: ███ 3000\n" +
                " 07-01-2022: ███ 3500\n" +
                " 08-01-2022: ████ 4000\n" +
                " 09-01-2022: █████ 4500\n" +
                " 10-01-2022: █████ 5000\n" +
                " 11-01-2022: ██████████████████████ 20000\n" +
                " 12-01-2022: ██████████████████████████████████████ 35000\n" +
                " 13-01-2022: ███████████████████████████████████████████████████ 46000\n" +
                " 14-01-2022: █████████████████████████████████████████████████████████████████████████████ 70000\n" +
                " 15-01-2022: ████████████████████████████████████████████████████████████████████████████████████████████████████ 90000\n" +
                "\n" +
                "========================================================================================================================";

        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");
        assertEquals(this.output, out.toString().trim());
    }

    @Test
    void testTargetOptionInvalidCaseOne() {
        this.output = "Missing required parameter for option '--target' (<targetPath>)\n" +
                "Usage: ubv [-hV] -t=<targetPath> [-p=<\"dd-MM-yyyy\"> [<\"dd-MM-yyyy\">]]...\n" +
                "User-Base Visualiser\n" +
                "  -p, --period=<\"dd-MM-yyyy\"> [<\"dd-MM-yyyy\">]\n" +
                "                  The date or dates used to filter the data by\n" +
                "  -t, --target=<targetPath>\n" +
                "                  The file whose data to graph\n" +
                "  -h, --help      Show this help message and exit.\n" +
                "  -V, --version   Print version information and exit.";

        new CommandLine(new UserBaseApp()).execute("-t");
        assertEquals(this.output, err.toString().trim());
    }

    @Test
    void testTargetOptionInvalidCaseTwo() {
        new CommandLine(new UserBaseApp()).execute("-t", "hsgvsvjsk/shsskj/x.z");
        assertEquals("Invalid URI host: null (authority: null)", out.toString().trim());
    }

    @Test
    void testPeriodOptionValidCaseOne() {
        this.output = "========================================================================================================================\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\tNumber of Active Users by Date\n" +
                "========================================================================================================================\n" +
                "\n" +
                " 01-01-2022: ████████████████████████████████████████████████████████████████████████████████████████████████████ 300\n" +
                "\n" +
                "========================================================================================================================";
        
        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/", "-p", "01-01-2022");
        assertEquals(this.output, out.toString().trim());
    }

    @Test
    void testPeriodOptionCaseTwo() {
        this.output = "========================================================================================================================\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\tNumber of Active Users by Date\n" +
                "========================================================================================================================\n" +
                "\n" +
                " 01-01-2022: ████████ 300\n" +
                " 02-01-2022: ██████████████ 500\n" +
                " 03-01-2022: ████████████████████ 700\n" +
                " 04-01-2022: █████████████████████████████████████ 1300\n" +
                " 05-01-2022: █████████████████████████████████████████████████████████ 2000\n" +
                " 06-01-2022: █████████████████████████████████████████████████████████████████████████████████████ 3000\n" +
                " 07-01-2022: ████████████████████████████████████████████████████████████████████████████████████████████████████ 3500\n" +
                "\n" +
                "========================================================================================================================";
        
        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/","-p", "01-01-2022",
                "07-01-2022");
        assertEquals(this.output, out.toString().trim());
    }

    @Test
    void testPeriodOptionInvalidCaseOne() {
        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/", "-p", "01-01-2023");

        assertEquals("Date '01-01-2023' does not exist", out.toString().trim());
    }

    @Test
    void testPeriodOptionInvalidCaseTwo() {
        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/", "-p", "2x-t6-y42w");

        assertEquals("Invalid date format string. See --help", out.toString().trim());
    }

    @Test
    void testPeriodOptionInvalidCaseThree() {
        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/", "-p", "07-01-2022",
                        "01-01-2022");

        assertEquals("Period is invalid, endDate cannot be smaller than startDate", out.toString().trim());
    }

    @Test
    void testPeriodOptionInvalidCaseFour() {
        new CommandLine(new UserBaseApp())
                .execute("-t", "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/", "-p", "01-01-2009",
                        "07-01-2022");

        assertEquals("Period is invalid, either one or both dates does not exist", out.toString().trim());
    }
}
