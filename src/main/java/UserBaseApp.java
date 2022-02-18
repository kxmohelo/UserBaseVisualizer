import graph.UserBaseGraph;
import http.UserBaseClient;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.*;

/**
 *
 */
@Command(
        name = "ubv",
        mixinStandardHelpOptions = true,
        sortOptions = false,
        version =  "ubv 1.0",
        description = "User-Base Visualiser"
)
public class UserBaseApp implements Runnable {
    private final UserBaseClient client;
    private final UserBaseGraph userBaseGraph;

    @Option(
            names = {"-p", "--period"},
            paramLabel = "<\"dd-MM-yyyy\">",
            type = String.class,
            description = "The date or dates used to filter the data by",
            arity = "1..2"
    )
    private static String[] dates;

    @Option(
            names = {"-t", "--target"},
            description = "The file whose data to graph",
            required = true,
            arity = "1"
    )
    private static String targetPath;

    /**
     * UserBaseApp empty constructor
     */
    public UserBaseApp() {
        this.client = new UserBaseClient();
        this.userBaseGraph = new UserBaseGraph();
        try {
            client.start();
        } catch (Exception ex) {
            System.out.println("Failed to start client");
        }
    }

    @Override
    public void run() {
        try {
            client.makeRequest(targetPath);
            SortedMap<String, Integer> data = client.getContentAsMap();

            if (dates != null) {
                if (dates.length == 1) {
                    userBaseGraph.drawGraphByDate(data, dates[0]);
                } else if (dates.length == 2) {
                    userBaseGraph.drawGraphByPeriod(data, dates[0], dates[1]);
                }
            } else {
                userBaseGraph.drawGraph(data);
            }

            client.stop();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        (new CommandLine(new UserBaseApp())).execute(args);
        System.exit(0);
    }
}
