package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import urlshortener.demo.controller.QrApi;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.utils.*;

import javax.servlet.http.HttpServletRequest;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
@Transactional
public class QrApiController implements QrApi {

    private static final Logger log = LoggerFactory.getLogger(QrApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public QrApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<QRItem> getQR(@ApiParam(value = "",required=true) @PathVariable("id") String id) {

        String accept = request.getHeader("Accept");
        String width = request.getParameter("width");
        String height = request.getParameter("height");

        // Check that width and heigth params are not null or negative
        // and return int
        int w = StringChecker.checkString2Int(width);
        int h = StringChecker.checkString2Int(height);

        QRItem qr = new QRItem();
        qr.setUri(id);
        qr.convertBase64(w, h);
        return new ResponseEntity<QRItem>(qr, HttpStatus.OK);
    }

}
