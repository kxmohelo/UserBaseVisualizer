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
            this.client.start();
        } catch (Exception ex) {
            System.out.println("Failed to start client");
        }
    }

    @Override
    public void run() {
        try {
            this.client.makeRequest(targetPath);
            SortedMap<String, Integer> data = this.client.getContentAsMap();

            if (this.dates != null) {
                if (this.dates.length == 1) {
                    this.userBaseGraph.drawGraphByDate(data, this.dates[0]);
                } else if (this.dates.length == 2) {
                    this.userBaseGraph.drawGraphByPeriod(data, this.dates[0], this.dates[1]);
                }
            } else {
                this.userBaseGraph.drawGraph(data);
            }

            this.client.stop();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        (new CommandLine(new UserBaseApp())).execute(args);
        System.exit(0);
    }
}
