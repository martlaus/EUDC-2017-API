package eudcApi.dao.tabbie;

import eudcApi.model.User;
import eudcApi.service.TournamentIdService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by mart on 18.07.17.
 */
public class TabbieRepository {
    public static final String EMAIL = "api@tallinneudc.com";
    public static final String PW = "tallinn";
    private String UTF8 = StandardCharsets.UTF_8.name();

    @Inject
    private TournamentIdService tournamentIdService;

    public String getRoundsByTournamentId(String id) {
        String url = "https://api.tabbie.org/rounds/filter?tournament_id=" + id;
        return getJSON(url);
    }

    public String getDebatesByRoundId(String id) {
        String url = "https://api.tabbie.org/debates/filter?round_id=" + id;
        return getJSON(url);
    }

    public String getTabbieRole(User user) {
        String id = tournamentIdService.getTournamentId();
        String url = "https://api.tabbie.org/users/gettournamentrole?user_id="
                + user.getTabbieId() + "&tournament_id=" + id;
        return getJSON(url);
    }

    public String getBarcode(String tabbieUserId) {
        String id = tournamentIdService.getTournamentId();
        String url = "https://api.tabbie.org/users/generatebarcode?user_id="
                + tabbieUserId + "&tournament_id=" + id;
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
        String email = user != null && user.getEmail() != null ? user.getEmail() : EMAIL;
        String pw = user != null && user.getPassword() != null ? user.getPassword() : PW;

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
