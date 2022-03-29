package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final Map<String, String> gameInfo;
    private final Client client;
    public Server(int port, Map<String, String> gameInfo, Client client) {
        this.gameInfo = gameInfo;
        this.port = port;
        this.client = client;
    }

    public void start(){
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            HttpServer httpServer = HttpServer.create(socketAddress, 0);
            System.out.println("Server start at port : " + port);
            StartGameHandler startGameHandler = new StartGameHandler(gameInfo, client);
            httpServer.createContext("/ping", new PingHandler());
            httpServer.createContext("/api/game/start", startGameHandler);
            httpServer.createContext("/api/game/fire", new FireHandler(gameInfo));
            httpServer.setExecutor(executorService);
            httpServer.start();
        } catch (IOException e) {e.printStackTrace();}
    }
}
