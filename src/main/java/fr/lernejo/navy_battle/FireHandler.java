package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

public class FireHandler implements HttpHandler {
    private final Map<String, String> gameInfo;
    private final Client client;
    public FireHandler(Map<String, String> gameInfo, Client client) {
        this.gameInfo = gameInfo;
        this.client = client;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestFire requestFire = new RequestFire(EnumConsequence.miss, true);
        String messageSend = objectMapper.writeValueAsString(requestFire);
        exchange.getResponseHeaders().add("Content-type", "application/json");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, messageSend.getBytes().length);
        exchange.getResponseBody().write(messageSend.getBytes());

        client.fire(gameInfo.get("client_url"), "B7");
    }
}
