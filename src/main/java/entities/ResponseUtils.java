package entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ResponseUtils {

    public static <T> T unmarshall(CloseableHttpResponse httpResponse, Class<T> usersClass) throws IOException {
        String jSonBody = EntityUtils.toString(httpResponse.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jSonBody, usersClass);
    }

    public  static  <E> String marshall(E clazz) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(clazz);
    }
}
