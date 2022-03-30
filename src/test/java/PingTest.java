import fr.lernejo.navy_battle.Client;
import fr.lernejo.navy_battle.Launcher;
import fr.lernejo.navy_battle.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
public class PingTest {
    @Test
    void testPing() throws IOException, InterruptedException {
        int port = 8909;
        String[] args = {String.valueOf(port)};
        Launcher.main(args);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/ping"))
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK, httpResponse.statusCode());
        Assertions.assertEquals("OK", httpResponse.body());
    }
}
