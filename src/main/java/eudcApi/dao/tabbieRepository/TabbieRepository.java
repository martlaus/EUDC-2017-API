package eudcApi.dao.tabbieRepository;

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
    String UTF8 = StandardCharsets.UTF_8.name();

    public String getRoundsByTournamentId(String id) {
        id = id != null ? id : "10"; //todo: remove when we have configurable tournament id


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.tabbie.org/rounds/filter?tournament_id=" + id);
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials("martlaus1@gmail.com", "lindemans"),
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
