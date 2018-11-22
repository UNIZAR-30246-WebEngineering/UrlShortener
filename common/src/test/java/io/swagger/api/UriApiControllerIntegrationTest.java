package io.swagger.api;

import io.swagger.model.URICreate;
import io.swagger.model.URIItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class UriApiControllerIntegrationTest {

    @Autowired
    private UriApi api;

    @Test
    public void changeURITest() throws Exception {
        URICreate body = new URICreate();
        String name = "name_example";
        ResponseEntity<Void> responseEntity = api.changeURI(body, name);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void createURITest() throws Exception {
        URICreate body = new URICreate();
        ResponseEntity<URIItem> responseEntity = api.createURI(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
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
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
