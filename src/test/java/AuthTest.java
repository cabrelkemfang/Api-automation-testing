import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthTest extends BaseClass {

    CloseableHttpClient httpClient;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        httpClient = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void close() throws IOException {
        httpClient.close();
        response.close();
    }

    @Test
    public void login() {
        HttpPost httpPost= new HttpPost(BASE_URL+"login");

        HttpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("grant_type", "password"));
        formparams.add(new BasicNameValuePair("client_id", "seven-pay-client"));
        formparams.add(new BasicNameValuePair("username", "value2"));
        formparams.add(new BasicNameValuePair("password", "value2"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

//        .formParam("grant_type", "password")
//                .formParam("client_id", "seven-pay-client")
//                .formParam("username", "${username}")
//                .formParam("password", "${password}")
//                .basicAuth("seven-pay-client", "seven-pay-secret")
//                .check(status is 200)
        httpPost.setEntity(entity);
    }

}
