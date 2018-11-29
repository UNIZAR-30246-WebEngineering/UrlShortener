package urlshortener.common.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.common.domain.URICreate;
import urlshortener.common.domain.URIItem;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UriApiControllerIntegrationTest {

    @Autowired
    private UriApi api;

    @Test
    public void changeURITest() throws Exception {
        URICreate body = new URICreate();
        String name = "name_example";
        ResponseEntity<URIItem> responseEntity = api.changeURI(body, name);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createURITest() throws Exception {
        URICreate body = new URICreate();
        ResponseEntity<URIItem> responseEntity = api.createURI(body);
        assertEquals(HttpStatus.TEMPORARY_REDIRECT, responseEntity.getStatusCode());
    }

    @Test
    public void deleteURITest() throws Exception {
        String id = "id_example";
        ResponseEntity<Void> responseEntity = api.deleteURI(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getURITest() throws Exception {
        String id = "id_example";
        ResponseEntity<Void> responseEntity = api.getURI(id);
        assertEquals(HttpStatus.TEMPORARY_REDIRECT, responseEntity.getStatusCode());
    }

}
