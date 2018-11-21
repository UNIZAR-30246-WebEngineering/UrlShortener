/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ErrorItem;
import io.swagger.model.URICreate;
import io.swagger.model.URIItem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Api(value = "uri", description = "the uri API")
public interface UriApi {

    @ApiOperation(value = "Assigns the name of a redirection", nickname = "changeURI", notes = "Assigns the name of a redirection", tags={ "F3 -  The app will let the user choose a name for the shorted URI", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Name has been assigned successfully"),
        @ApiResponse(code = 400, message = "Error creating resource", response = ErrorItem.class) })
    @RequestMapping(value = "/uri/{name}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body,@ApiParam(value = "",required=true) @PathVariable("name") String name);


    @ApiOperation(value = "Creates a new redirection", nickname = "createURI", notes = "Create a new URI redirection ", response = URIItem.class, tags={ "F0 - The app will short, storage and get URI&#39;s", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The URI redirection has been successfully created", response = URIItem.class) })
    @RequestMapping(value = "/uri",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<URIItem> createURI(@ApiParam(value = "URI" ,required=true )  @Valid @RequestBody URICreate body);


    @ApiOperation(value = "Deletes an existing URI and its content.", nickname = "deleteURI", notes = "Remove a URI redirection ", tags={ "F0 - The app will short, storage and get URI&#39;s", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Resource has been removed successfully"),
        @ApiResponse(code = 404, message = "There is no URI with that hash") })
    @RequestMapping(value = "/uri/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteURI(@ApiParam(value = "",required=true) @PathVariable("id") String id);


    @ApiOperation(value = "Returns the data of a redirection", nickname = "getURI", notes = "Get a URI redirection ", tags={ "F0 - The app will short, storage and get URI&#39;s", })
    @ApiResponses(value = { 
        @ApiResponse(code = 307, message = "Redirect to the real URI") })
    @RequestMapping(value = "/uri/{id}",
        method = RequestMethod.GET)
    ResponseEntity<Void> getURI(@ApiParam(value = "",required=true) @PathVariable("id") String id);

}