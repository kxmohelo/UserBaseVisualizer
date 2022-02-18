package http;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.SortedMap;

/**
 * UserBaseClient Class
 */
public class UserBaseClient {
    private HttpClient client;
    private SortedMap<String, Integer> contentAsMap;

    /**
     * Starts the HttpClient
     * @throws Exception
     */
    public void start() throws Exception {
        client = new HttpClient();
        client.start();
    }

    /**
     * Stops the HttpClient
     * @throws Exception
     */
    public void stop() throws Exception {
        client.stop();
    }

    /**
     * Makes a get request from the endpoint, url
     * @param url The endpoint for the request
     * @return Content response of the request
     * @throws Exception
     */
    public ContentResponse makeRequest(String url) throws Exception {
        ContentResponse contentResponse = client.GET(url);
        String contentAsString = contentResponse.getContentAsString();
        this.contentAsMap = new ObjectMapper().readValue(contentAsString, SortedMap.class);

        return contentResponse;
    }

    /**
     * Gets the content as a map from the request made
     * @return Sorted map of the content
     */
    public SortedMap<String, Integer> getContentAsMap() {
        return this.contentAsMap;
    }

    /**
     * Checks if the HttpClient is running
     * @return True, if the client is not running, else false
     */
    public boolean isConnected() {
        return client.isRunning();
    }
}
