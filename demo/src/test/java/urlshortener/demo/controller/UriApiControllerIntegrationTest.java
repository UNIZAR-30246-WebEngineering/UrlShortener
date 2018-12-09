package urlshortener.demo.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.IncorrectHashPassException;
import urlshortener.demo.exception.InvalidRequestParametersException;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.URIRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UriApiControllerIntegrationTest {

    @Autowired
    private UriApi api;

    @Autowired
    private URIRepository repository;

    private URICreate correctURI, invalidURI, emptyURI;

    private URIItem createdURIItem;

    @After
    public void cleanUp(){
        repository.removeAll();
    }

    @Before
    public void prepareTest(){
        correctURI = new URICreate().uri("https://www.google.es");
        invalidURI = new URICreate().uri(null);
        emptyURI = new URICreate().uri("");

        createdURIItem = (URIItem) new URIItem().id("testID").redirection("http://google.es").hashpass("abc");
        repository.add(createdURIItem);
    }

    /*
     * Test PUT /uri/{id}
     */
    @Test
    public void createWithNameOK(){
        //Test 1: Create OK
        String uriName = "testURI";
        ResponseEntity<URIItem> response = api.changeURI(correctURI, uriName);
        URIItem item = response.getBody();

        assertNotNull(item);
        assertEquals(uriName, item.getId());
        assertEquals(correctURI.getUri(), item.getRedirection());
    }

    @Test
    public void createWithNameInvalidURL(){
        //Test 2: Create with invalid URL
        String uriName = "testURI";

        try {
            api.changeURI(invalidURI, uriName);
            fail();
        }catch (InvalidRequestParametersException ignored){ }

        try {
            api.changeURI(emptyURI, uriName);
            fail();
        }catch (InvalidRequestParametersException ignored){ }
    }

    @Test
    public void createWithNameInvalidHash(){
        //Test 3: Create with invalid hash

        try {
            api.changeURI(correctURI, null);
            fail();
        }catch (InvalidRequestParametersException ignored){ }

        try {
            api.changeURI(correctURI, "");
            fail();
        }catch (InvalidRequestParametersException ignored){ }
    }

    /*
     * Test PUT /uri
     */
    @Test
    public void createOK(){
        //Test 1: Create OK
        ResponseEntity<URIItem> response = api.createURI(correctURI);
        URIItem item = response.getBody();

        assertNotNull(item);
        assertEquals(correctURI.getUri(), item.getRedirection());
    }

    @Test
    public void createInvalidURL(){
        //Test 2: Create with invalid URL
        try {
            api.createURI(invalidURI);
            fail();
        }catch (InvalidRequestParametersException ignored){ }

        try {
            api.createURI(emptyURI);
            fail();
        }catch (InvalidRequestParametersException ignored){ }
    }

    /*
     * Test DELETE /uri
     */

    @Test
    public void deleteURIOK() {
        ResponseEntity<Void> responseEntity = api.deleteURI(createdURIItem.getId(), createdURIItem.getHashpass());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(repository.contains(createdURIItem.getId()));
    }

    @Test
    public void deleteURIInvalidHashpash() {
        String hashpass = null;
        try {
            api.deleteURI(createdURIItem.getId(), hashpass);
            fail();
        }catch (InvalidRequestParametersException ignored){}
        hashpass = "";
        try {
            api.deleteURI(createdURIItem.getId(), hashpass);
            fail();
        }catch (InvalidRequestParametersException ignored){}

        assertTrue(repository.contains(createdURIItem.getId()));
    }

    @Test
    public void deleteURIIcorrectHashpash() {
        String hashpass = createdURIItem.getHashpass() + "invalid";
        try {
            api.deleteURI(createdURIItem.getId(), hashpass);
            fail();
        }catch (InvalidRequestParametersException | IncorrectHashPassException ignored){}
        assertTrue(repository.contains(createdURIItem.getId()));
    }

    @Test
    public void deleteURIIncorrectID() {
        String hashpass = createdURIItem.getHashpass();
        try {
            api.deleteURI(createdURIItem.getId() + "randomID", hashpass);
            fail();
        }catch (InvalidRequestParametersException | UnknownEntityException ignored){}
        assertTrue(repository.contains(createdURIItem.getId()));
    }

    @Test
    public void deleteURIInvalidID() {
        String hashpass = createdURIItem.getHashpass();
        try {
            api.deleteURI("", hashpass);
            fail();
        }catch (InvalidRequestParametersException ignored){}
        assertTrue(repository.contains(createdURIItem.getId()));

        try {
            api.deleteURI(null, hashpass);
            fail();
        }catch (InvalidRequestParametersException ignored){}
        assertTrue(repository.contains(createdURIItem.getId()));
    }



    /*
     * Test GET /uri
     */
    @Test
    public void getURITestError() {
        String id = "id_example";
        try {
            api.getURI(id);
            fail();
        }catch(UnknownEntityException ignored){ }
    }

    @Test
    public void getURITestOK() {
        String id = "id_example";
        repository.add((URIItem) new URIItem().id(id).redirection("http://google.es").hashpass("abc"));

        ResponseEntity<Void> responseEntity = api.getURI(id);
        assertEquals(HttpStatus.TEMPORARY_REDIRECT, responseEntity.getStatusCode());
    }

    @Test
    public void getURITestTooManyRequests() {
        final long MAX_REDIRECTIONS = 100;
        String id = "id_example";
        repository.add((URIItem) new URIItem().id(id).redirection("http://google.es").hashpass("abc"));
        for(int i = 0; i < MAX_REDIRECTIONS; i++){
            repository.get(id);
        }

        ResponseEntity<Void> responseEntity = api.getURI(id);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, responseEntity.getStatusCode());
    }

}
