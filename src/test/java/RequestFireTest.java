import fr.lernejo.navy_battle.EnumConsequence;
import fr.lernejo.navy_battle.Launcher;
import fr.lernejo.navy_battle.RequestFire;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestFireTest {
    @Test
    void testMiss(){
        RequestFire requestFire = new RequestFire(EnumConsequence.miss, true);

        Assertions.assertEquals(EnumConsequence.miss, requestFire.getConsequence());
        Assertions.assertTrue(requestFire.isShipLeft());
    }
    @Test
    void testChunk(){
        RequestFire requestFire = new RequestFire(EnumConsequence.chunk, true);

        Assertions.assertEquals(EnumConsequence.chunk, requestFire.getConsequence());
        Assertions.assertTrue(requestFire.isShipLeft());
    }
    @Test
    void testFire(){
        RequestFire requestFire = new RequestFire(EnumConsequence.hit, true);

        Assertions.assertEquals(EnumConsequence.hit, requestFire.getConsequence());
        Assertions.assertTrue(requestFire.isShipLeft());
    }
}
