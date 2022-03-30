import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lernejo.navy_battle.Launcher;
import fr.lernejo.navy_battle.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StartGameTest {
    @Test
    void testStartGame() throws IOException, InterruptedException {
        int port = 8908;
        String client_id = "id";
        String client_port = "8765";

        String[] args = {String.valueOf(port)};

        Launcher.main(args);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"" + client_id + "\", \"url\":\"http://localhost:" + client_port + "\", \"message\":\"hello\"}"))
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        Request requestReceive = objectMapper.readValue(httpResponse.body(), Request.class);

        String expectedMessage = "May the best code win";
        String expectedUrl = "http://localhost:" + port;
        Assertions.assertEquals(HttpURLConnection.HTTP_ACCEPTED, httpResponse.statusCode());
        Assertions.assertEquals(expectedMessage, requestReceive.getMessage());
        Assertions.assertEquals(expectedUrl, requestReceive.getUrl());
    }
}
