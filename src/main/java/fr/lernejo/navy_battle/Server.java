package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void start(){
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            HttpServer httpServer = HttpServer.create(socketAddress, 0);
            System.out.println("Server start at port : " + port);
            httpServer.setExecutor(executorService);
            httpServer.createContext("/ping", new Ping());
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
