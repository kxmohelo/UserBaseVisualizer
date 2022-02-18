import graph.UserBaseGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


public class UserBaseGraphTest {
    static SortedMap<String, Integer> data;
    UserBaseGraph userBaseGraph = new UserBaseGraph();

    @BeforeAll
    static void setData() {
        data = new TreeMap<>();

        data.put("01-01-2022", 300);
        data.put("02-01-2022", 500);
        data.put("03-01-2022", 700);
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

        userBaseGraph.drawGraph(data);

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

        userBaseGraph.drawGraphByDate(data, "01-01-2022");

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

        userBaseGraph.drawGraphByPeriod(data, "02-01-2022", "03-01-2022");

        assertEquals(output, os.toString().trim());
        System.setOut(console);
    }
}
