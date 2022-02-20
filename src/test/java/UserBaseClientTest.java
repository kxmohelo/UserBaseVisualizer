import com.fasterxml.jackson.databind.ObjectMapper;
import http.UserBaseClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.jupiter.api.*;

import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

public class UserBaseClientTest {
    private UserBaseClient client = new UserBaseClient();

    @BeforeEach
    void startClient() throws Exception {
        this.client.start();
    }

    @AfterEach
    void stopClient() throws Exception {
        this.client.stop();
    }

    @Test
    void testStart() {
        assertTrue(this.client.isConnected());
    }

    @Test
    void testStop() throws Exception {
        this.client.stop();

        assertFalse(this.client.isConnected());
    }

    @Test
    void testMakeRequest() throws Exception {
        ContentResponse res = this.client.makeRequest("http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");

        assertEquals(res.getStatus(), 200);
    }

    @Test
    void getContentAsMap() throws Exception {
        String content = "{\n" +
                "    \"01-01-2022\":300,\n" +
                "    \"02-01-2022\":500,\n" +
                "    \"03-01-2022\":700,\n" +
                "    \"04-01-2022\":1300,\n" +
                "    \"05-01-2022\":2000,\n" +
                "    \"06-01-2022\":3000,\n" +
                "    \"07-01-2022\":3500,\n" +
                "    \"08-01-2022\":4000,\n" +
                "    \"09-01-2022\":4500,\n" +
                "    \"10-01-2022\":5000,\n" +
                "    \"11-01-2022\":20000,\n" +
                "    \"12-01-2022\":35000,\n" +
                "    \"13-01-2022\":46000,\n" +
                "    \"14-01-2022\":70000,\n" +
                "    \"15-01-2022\":90000\n" +
                "}";

        SortedMap<String, Integer> map = new ObjectMapper().readValue(content, SortedMap.class);

        ContentResponse res = client.makeRequest("http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");

        assertEquals(res.getStatus(), 200);
        assertEquals(client.getContentAsMap(), map);
    }
}
