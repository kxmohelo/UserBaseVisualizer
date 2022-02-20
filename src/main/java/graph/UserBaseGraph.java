package graph;

import java.time.Instant;
import java.util.*;

/**
 * UserBaseGraph Class
 */
public class UserBaseGraph {
    private final String[] typeOfBars = {"▏", "▎", "▍", "▌", "▋", "▊", "▉", "█"};
    private double term;

    /**
     * Draws graph of the specified data
     * @param data the data used for graphing
     */
    public void drawGraph(SortedMap<String, Integer> data) {
        this.setTerm(data.values().toArray(new Integer[0]));

        System.out.println("\n" + "===".repeat(40));

        String title = "\n" + "\t".repeat(11) + "Number of Active Users by Date\n" + "===".repeat(40) + "\n";

        System.out.println(title);

        String[] dates = data.keySet().toArray(new String[0]);
        Integer[] users = data.values().toArray(new Integer[0]);

        String line;
        for (int i = 0; i < data.size(); i++) {
            line = " " + dates[i] + ": " + this.createBarByUserActivity(users[i]) + " " + users[i];
            System.out.println(line);
        }

        System.out.println("\n" + "===".repeat(40) + "\n");
    }

    /**
     * Draws graph by a specific date
     * @param data The data used for graphing
     * @param date Date used to filter the data
     */
    public void drawGraphByDate(SortedMap<String, Integer> data, String date) {
        if (!this.dateIsValid(date)) {
            throw new IllegalArgumentException("Invalid date format string. See --help");
        } else if (!data.containsKey(date)) {
            throw new IllegalArgumentException("Date '" + date + "' does not exist");
        }

        Integer userActivity = data.get(date);
        data.clear();
        data.put(date, userActivity);

        drawGraph(data);
    }

    /**
     * Draws graph by a specific period, between the start date and end date inclusively
     * @param data The data used for graphing
     * @param startDate Date used to filter the data as lower limit
     * @param endDate Date used to filter the data as upper limit
     */
    public void drawGraphByPeriod(SortedMap<String, Integer> data, String startDate, String endDate) {
        if (!this.dateIsValid(startDate) || !this.dateIsValid(endDate)) {
            throw new IllegalArgumentException("Invalid date format string. See --help");
        }
        else if (!data.containsKey(startDate) || !data.containsKey(endDate)) {
            throw new IllegalArgumentException("Period is invalid, either one or both dates does not exist");
        }
        else if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("Period is invalid, endDate cannot be smaller than startDate");
        }
        else {

            if (startDate.equals(endDate)) {
                drawGraphByDate(data, startDate);
            } else {
                drawGraph(
                        ((TreeMap<String, Integer>) data).subMap(startDate, true, endDate, true)
                );
            }
        }
    }

    /**
     * Sets the interval for the data when graphing
     * @param userActivity The array containing the number of user activity in the data
     * @return The term for each of each interval
     */
    private void setTerm(Integer[] userActivity) {
        double max = Collections.max(List.of(userActivity));
        this.term = max / 100.0;
    }


    /**
     * Creates a graph-bar representation for a user activity set in the data
     * @param userActivity The number of user activity
     * @return Bar representation of user activity as a string
     */
    private String createBarByUserActivity(double userActivity) {
        String bar = "";

        if ((int)userActivity / (int)this.term > 0) {
            bar += this.typeOfBars[7].repeat((int)userActivity / (int)this.term);

            while (userActivity > this.term) {
                userActivity -= this.term;
            }
        }
        else if (userActivity != 0) {

            double i = 0.125;
            int barIndex = 0;

            while (userActivity >= (this.term * i) && i < 1.0) {
                barIndex++;
                i += 0.125;
            }

            bar += this.typeOfBars[barIndex];
        }

        return bar;
    }

    /**
     * Checks if date is valid
     * Added because I got "Invalid date format string. See --help"
     * for ....-p '01-01-2022' '13-01-2022'
     * @param date The number of user activity
     * @return boolean saying of date is valid
     * NB: didn't want to over complicate and cater for lots of date (just wanted to test),
     * so assumes day and month are passed as 2 characters
     */
    private boolean dateIsValid(String date){
        return date.matches("^([0-9]|0[1-9]){2,}-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$");
    }
}
