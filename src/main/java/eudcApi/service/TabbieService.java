package eudcApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eudcApi.model.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Map;

public class TabbieService {

    public User getTabbieUser(User loginData) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://api.tabbie.org/users/me");
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(loginData.getEmail(), loginData.getPassword()),
                "UTF-8", false));

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity responseEntity = httpResponse.getEntity();


        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(responseEntity.getContent(), Map.class);

        User user = new User();
        user.setEmail((String) jsonMap.get("email"));
//        user.setTabbieToken((String) jsonMap.get("token"));
        return user;
    }
}
