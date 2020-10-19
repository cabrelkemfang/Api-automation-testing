import entities.DomiUsers;
import entities.NewUsers;
import entities.Response;
import entities.ResponseUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUsers extends BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setUp() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResponse() throws IOException {
        client.close();
        response.close();
    }

    @Test(priority = 1)
    public void getUser() throws IOException {
        HttpGet httpGet = new HttpGet(TEST_BASE_ENDPOINT + "users/2");
        //Execute the request
        response = client.execute(httpGet);
        //get the status code
        int statusCode = response.getStatusLine().getStatusCode();
        //Get content type
        ContentType contentType = ContentType.getOrDefault(response.getEntity());
        //Get response body
        DomiUsers domiUsers = ResponseUtils.unmarshall(response, DomiUsers.class);

        //Assert the result
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(contentType.getMimeType(), "application/json");
        Assert.assertEquals(domiUsers.getData().getId(), Long.valueOf(2));
        Assert.assertEquals(domiUsers.getData().getEmail(), "janet.weaver@reqres.in");
        Assert.assertNotNull(domiUsers.getAd().getUrl());
    }

    @Test(priority = 2)
    public void createNewUser() throws IOException {
        HttpPost httpPost = new HttpPost(TEST_BASE_ENDPOINT + "users");

        //Define the Json
        NewUsers newUsers = new NewUsers();
        newUsers.setJob("devop");
        newUsers.setName("cabrel");
        String json = ResponseUtils.marshall(newUsers);

        //Adding json to the request
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        //Execute the request
        response = client.execute(httpPost);

        //Get response body
        Response unmarshall = ResponseUtils.unmarshall(response, Response.class);

        //Get status code
        int statusCode = response.getStatusLine().getStatusCode();
        //Assert
        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(newUsers.getJob(),unmarshall.getJob());
        Assert.assertEquals(newUsers.getName(),unmarshall.getName());
    }

}
