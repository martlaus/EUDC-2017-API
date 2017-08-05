package eudcApi.dao.tabbieRepository;

import eudcApi.model.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by mart on 18.07.17.
 */
public class TabbieRepository {
    public static final String EMAIL = "api@tallinneudc.com";
    public static final String PW = "tallinn";
    private String UTF8 = StandardCharsets.UTF_8.name();

    public String getRoundsByTournamentId(String id) {
        id = id != null ? id : "10"; //todo: remove when we have configurable tournament id

        String url = "https://api.tabbie.org/rounds/filter?tournament_id=" + id;
        return getJSON(url);
    }

    public String getTabbieRole(User user) {
        String url = "https://api.tabbie.org/users/gettournamentrole?user_id=" + user.getTabbieId() + "&tournament_id=357";
        return getJSON(url);
    }

    public String doTabbieLogin(User user) {
        String url = "https://api.tabbie.org/users/me";
        return getJSON(url, user);
    }

    private String getJSON(String url) {
        return getJSON(url, null);
    }

    private String getJSON(String url, User user) {
        String email = user.getEmail() != null ? user.getEmail() : EMAIL;
        String pw = user.getPassword() != null ? user.getPassword() : PW;

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(email, pw),
                UTF8, false));

        HttpResponse httpResponse;
        String text = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();

            text = IOUtils.toString(responseEntity.getContent(), UTF8);
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
