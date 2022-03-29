package fr.lernejo.navy_battle;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Entrez un port");
            return;
        }
        int port = Integer.parseInt(args[0]);
        final Map<String, String> gameInfo = new HashMap<>();
        gameInfo.put("id", UUID.randomUUID().toString());
        gameInfo.put("port", String.valueOf(port));
        Client client = new Client(HttpClient.newHttpClient());
        Server server = new Server(port, gameInfo, client);
        server.start();
        if (args.length == 2) {
            gameInfo.put("client_url", args[1]);
            client.start(args[1], gameInfo);
        }
    }
}
