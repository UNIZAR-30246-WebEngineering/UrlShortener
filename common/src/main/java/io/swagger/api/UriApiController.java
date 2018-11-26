package io.swagger.api;

import io.swagger.model.ErrorItem;
import io.swagger.model.URICreate;
import io.swagger.model.URIItem;
import io.swagger.model.URIUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

@Controller
public class UriApiController implements UriApi {

    private static final Logger log = LoggerFactory.getLogger(UriApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UriApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<URIItem> changeURI(@Valid URIUpdate body, String name) {
        return null;
    }

    public ResponseEntity<URIItem> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body, @ApiParam(value = "",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");

        ErrorItem error = new ErrorItem();
        error.setErrorInfo("This is a test error");        

        return new ResponseEntity<URIItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<URIItem> createURI(@ApiParam(value = "URI" ,required=true )  @Valid @RequestBody URICreate body) {
        String accept = request.getHeader("Accept");

        URIItem uri = new URIItem();
        uri.setId("");
        uri.setRedirection("https://google.es");


        return new ResponseEntity<URIItem>(uri, HttpStatus.TEMPORARY_REDIRECT);
    }

    @Override
    public ResponseEntity<Void> deleteURI(String id, @NotNull @Valid String hashpass) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteURI(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getURI(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");

        ResponseEntity<Void> r = new ResponseEntity<Void>(HttpStatus.TEMPORARY_REDIRECT);


        URI location = null;
        try {
            location = new URI("https://google.es");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return new ResponseEntity<Void>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

}
