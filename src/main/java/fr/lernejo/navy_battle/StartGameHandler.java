package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

public class StartGameHandler implements HttpHandler {
    private final Map<String, String> gameInfo;
    private final Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public StartGameHandler(Map<String, String> gameInfo, Client client) {
        this.gameInfo = gameInfo;
        this.client = client;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            try {
                InputStream response = exchange.getRequestBody();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Request requestReceive = objectMapper.readValue(response, Request.class);
                gameInfo.put("client_id", requestReceive.getId());
                gameInfo.put("client_url", requestReceive.getUrl());
            }catch (IOException e) {exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);}
            send(exchange);
        }
        else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
        }
        client.fire(gameInfo.get("client_url"), "B2");
    }

    private void send(HttpExchange exchange) throws IOException {
        Request requestSend = new Request(gameInfo.get("id"), "http://localhost:" + gameInfo.get("port"), "May the best code win");
        String messageSend = objectMapper.writeValueAsString(requestSend);
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, messageSend.getBytes().length);
        exchange.getResponseBody().write(messageSend.getBytes());
    }
}
