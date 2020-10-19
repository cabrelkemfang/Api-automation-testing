
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.ResponseUtils;
import entities.Users;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Get200 extends BaseClass {

    CloseableHttpClient client = HttpClientBuilder.create().build();
    CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResponse() throws IOException {
        client.close();
        httpResponse.close();
    }

    @Test(priority = 1)
    public void baseUrlReturn200() throws IOException {
        HttpGet httpGet = new HttpGet(BASE_ENDPOINT);

        httpResponse = client.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Header contentType = httpResponse.getEntity().getContentType();

        ContentType ct = ContentType.getOrDefault(httpResponse.getEntity());

        assertEquals(statusCode, 200);
        assertEquals(ct.getMimeType(), "application/json");
    }

    @Test(priority = 2)
    public void rateLimitReturn200() throws IOException {
        HttpGet httpGet = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        httpResponse = client.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String jsonBody = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(jsonBody);
        ContentType ct = ContentType.getOrDefault(httpResponse.getEntity());

        assertEquals(statusCode, 200);
        assertEquals(ct.getMimeType(), "application/json");
    }

    @Test(priority = 5)
    public void UserReturn200() throws IOException {
        HttpGet httpGet = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        // Execute request
        httpResponse = client.execute(httpGet);
        // status code
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        // Json Body
//        String jsonBody = EntityUtils.toString(httpResponse.getEntity());
//        System.out.println(jsonBody);
        //Content Type
        ContentType ct = ContentType.getOrDefault(httpResponse.getEntity());
        Users users = ResponseUtils.unmarshall(httpResponse, Users.class);

        assertEquals(statusCode, 200);
        assertEquals(ct.getMimeType(), "application/json");
        assertEquals(users.getLogin(), "andrejss88");
    }


    @Test(priority = 3)
    public void searchReturn200() throws IOException {
        HttpGet httpGet = new HttpGet(BASE_ENDPOINT + "search/repositories?q=java");

        httpResponse = client.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        assertEquals(statusCode, 200);
    }
}
