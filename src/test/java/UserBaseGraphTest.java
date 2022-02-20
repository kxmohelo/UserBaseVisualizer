import graph.UserBaseGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


public class UserBaseGraphTest {
    private static SortedMap<String, Integer> data;
    private UserBaseGraph userBaseGraph = new UserBaseGraph();

    @BeforeAll
    static void setData() {
        UserBaseGraphTest.data = new TreeMap<>();

        UserBaseGraphTest.data.put("01-01-2022", 300);
        UserBaseGraphTest.data.put("02-01-2022", 500);
        UserBaseGraphTest.data.put("03-01-2022", 700);
    }

    @Test
    void testDrawGraph() {
        String output = "========================================================================================================================\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\tNumber of Active Users by Date\n" +
                "========================================================================================================================\n" +
                "\n" +
                " 01-01-2022: ██████████████████████████████████████████ 300\n" +
                " 02-01-2022: ███████████████████████████████████████████████████████████████████████ 500\n" +
                " 03-01-2022: ████████████████████████████████████████████████████████████████████████████████████████████████████ 700\n" +
                "\n" +
                "========================================================================================================================";

        PrintStream console = System.out;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        this.userBaseGraph.drawGraph(UserBaseGraphTest.data);

        assertEquals(output, os.toString().trim());
        System.setOut(console);
    }

    @Test
    void testDrawGraphByDate() {
        String output = "========================================================================================================================\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\tNumber of Active Users by Date\n" +
                "========================================================================================================================\n" +
                "\n" +
                " 01-01-2022: ████████████████████████████████████████████████████████████████████████████████████████████████████ 300\n" +
                "\n" +
                "========================================================================================================================";

        PrintStream console = System.out;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        this.userBaseGraph.drawGraphByDate(UserBaseGraphTest.data, "01-01-2022");

        assertEquals(output, os.toString().trim());
        System.setOut(console);
    }

    @Test
    void testDrawGraphByPeriod() {
        String output = "========================================================================================================================\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\tNumber of Active Users by Date\n" +
                "========================================================================================================================\n" +
                "\n" +
                " 02-01-2022: ███████████████████████████████████████████████████████████████████████ 500\n" +
                " 03-01-2022: ████████████████████████████████████████████████████████████████████████████████████████████████████ 700\n" +
                "\n" +
                "========================================================================================================================";

        PrintStream console = System.out;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        this.userBaseGraph.drawGraphByPeriod(UserBaseGraphTest.data, "02-01-2022", "03-01-2022");

        assertEquals(output, os.toString().trim());
        System.setOut(console);
    }
}
