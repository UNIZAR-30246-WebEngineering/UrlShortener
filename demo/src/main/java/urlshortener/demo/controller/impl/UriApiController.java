package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import urlshortener.demo.controller.UriApi;
import urlshortener.demo.domain.ErrorItem;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.utils.CheckAlive;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.URIRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
public class UriApiController implements UriApi {

    private static final Logger log = LoggerFactory.getLogger(UriApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final URIRepository uriService;

    @org.springframework.beans.factory.annotation.Autowired
    public UriApiController(ObjectMapper objectMapper, HttpServletRequest request, URIRepository uriService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.uriService = uriService;
    }

    public ResponseEntity<URIItem> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body,@ApiParam(value = "",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");

        ErrorItem error = new ErrorItem();
        error.setErrorInfo("This is a test error");        

        return new ResponseEntity<URIItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<URIItem> createURI(@ApiParam(value = "URI" ,required=true )  @Valid @RequestBody URICreate body) {
        String accept = request.getHeader("Accept");
        CheckAlive c = new CheckAlive();

        URL url = null;
        HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;
        URIItem uri = new URIItem();
        uri.setId("");
        uri.setRedirection("");
        uri.setHashpass("");

        try {
            if (c.makeRequest(body.getUri()) == 200) {

                /*uri.setId("");
                uri.setRedirection("");
                uri.setHashpass("");*/

                //Completar método, rellenar el objeto de tipo URIItem "uri"

                return new ResponseEntity<URIItem>(uri, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<URIItem>(uri, HttpStatus.BAD_REQUEST);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<URIItem>(uri, httpStatus);
    }

    public ResponseEntity<Void> deleteURI(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getURI(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        CheckAlive c = new CheckAlive();
        URI location = null;
        String redirection = ""; //Se recupera de la BD la URI asociada al parámetro "id"


        URIItem item = uriService.get(id);
        if(item == null){
            throw new UnknownEntityException(1, "Unknown URI: " + id);
        }
        redirection = item.getRedirection();

        try {
            if (c.makeRequest(redirection) == 200){
                //OK
                //Para esa URI, se registra la fecha actual como útima fecha en la que estuvo viva
                location = new URI(redirection);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setLocation(location);
                return new ResponseEntity<Void>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
            }
            else {
                //Cualquier otra cosa aparte de un código 200 significará que la URI está muerta
                //Se obtiene la última vez que la URI estuvo viva
                //  -Si la diferencia entre la fecha actual y la fecha recuperada es >= K, entonces la URI se borra
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<Void>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

}
