import fr.lernejo.navy_battle.Launcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LauncherTest {
    @Test
    void testLauncherNoArgs() throws IOException, InterruptedException {
        String[] args = {};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        Launcher.main(args);

        System.out.flush();
        System.setOut(originalOut);

        String expected = "Entrez un port";
        String actual = out.toString();
        Assertions.assertTrue(actual.contains(expected));
    }
}
