package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Map;

public class Client {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void start(String url, Map<String, String> gameInfo) {
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + gameInfo.get("port") + "\", \"message\":\"hello\"}"))
            .build();
        client.sendAsync(requestPost, res-> {return null;});
    }
}
