package urlshortener.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.QRItem;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QrApiControllerIntegrationTest {

    @Autowired
    private QrApi api;

    @Test
    public void getQRTest() throws Exception {
        String id = "id_example";
        ResponseEntity<QRItem> responseEntity = api.getQR(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
