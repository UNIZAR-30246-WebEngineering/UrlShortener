package urlshortener.demo.controller;


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

public class CheckApiControllerIntegrationTest {

    @Autowired
    private CheckApi api;

    @Test
    public void checkURITest() throws Exception {
        String id = "id_example";
        ResponseEntity<Void> responseEntity = api.checkURI(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
