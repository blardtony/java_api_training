package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try {
            System.out.println("Server start at port " + port);
            new Server().start(port);
            //server.createContext("/api/game/start", Launcher::startGame );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void startGame(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-type", "application/json");
        String body = "Start";
        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        }
    }
}
