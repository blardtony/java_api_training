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
    private final Client client = new Client();
    public Server(int port, Map<String, String> gameInfo) {
        this.gameInfo = gameInfo;
        this.port = port;
    }

    public void start(){
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            HttpServer httpServer = HttpServer.create(socketAddress, 0);
            StartGameHandler startGameHandler = new StartGameHandler(gameInfo, client);

            System.out.println("Server start at port : " + port);

            httpServer.setExecutor(executorService);

            httpServer.createContext("/ping", new PingHandler());
            httpServer.createContext("/api/game/start", startGameHandler);
            httpServer.createContext("/api/game/fire", new FireHandler(gameInfo));
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
