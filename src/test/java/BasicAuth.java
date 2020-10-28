
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;

import java.util.ArrayList;
import java.util.List;

public class BasicAuth extends BaseClass {

    HttpGet request = new HttpGet("http://localhost:8080/books");

    CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials (AuthScope.ANY,
            new UsernamePasswordCredentials("user", "password")
        );

    List<NameValuePair> formparams = new ArrayList<NameValuePair>();
}
