package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Client {
    private final HttpClient client = HttpClient.newHttpClient();

    public void start(String url, Map<String, String> gameInfo) {
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + gameInfo.get("port") + "\", \"message\":\"hello\"}"))
            .build();
        client.sendAsync(requestPost,  HttpResponse.BodyHandlers.ofString());
        fire(url, "F4");
    }

    public void fire(String url, String cell) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url + "api/game/fire?cell=" + cell))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
