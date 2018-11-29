package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import urlshortener.demo.controller.StatsApi;
import urlshortener.demo.domain.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@RestController
public class StatsApiController implements StatsApi {

    private static final Logger log = LoggerFactory.getLogger(StatsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StatsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Stats> getStats() {
        String accept = request.getHeader("Accept");
        Stats stats = new Stats();
        stats.setRedirectedUris(745);
        stats.setGeneratedQr(452);
        stats.setServerLoad(new BigDecimal(0.23));
        return new ResponseEntity<Stats>(stats, HttpStatus.OK);
    }

}
