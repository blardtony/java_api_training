package fr.lernejo.navy_battle;

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
        final Map<String, String> gameInfo = new HashMap<String, String>();
        gameInfo.put("id", UUID.randomUUID().toString());
        gameInfo.put("port", String.valueOf(port));

        Server server = new Server(port, gameInfo);
        if (args.length == 2) {
            new Client().start(args[1], gameInfo);
            new Client().fire(args[1], "F4");
        }
        server.start();
    }
}
