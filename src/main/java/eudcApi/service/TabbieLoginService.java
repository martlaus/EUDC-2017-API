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

public class TabbieLoginService {

    public User getTabbieUser(User loginData) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://api.tabbie.org/users/me");
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(loginData.getEmail(), loginData.getPassword()),
                "UTF-8", false));

        HttpResponse httpResponse;
        Map<String, Object> jsonMap = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            jsonMap = mapper.readValue(responseEntity.getContent(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        User user = null;
        if ((jsonMap != null ? jsonMap.get("email") : null) != null) {
            user = new User();
            user.setEmail((String) jsonMap.get("email"));
//        user.setTabbieToken((String) jsonMap.get("token"));
        }

        return user;
    }
}
