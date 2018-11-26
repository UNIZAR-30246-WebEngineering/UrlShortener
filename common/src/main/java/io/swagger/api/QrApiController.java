package io.swagger.api;

import io.swagger.model.QRItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

@Controller
public class QrApiController implements QrApi {

    private static final Logger log = LoggerFactory.getLogger(QrApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public QrApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ResponseBody
    public ResponseEntity<QRItem> getQR(@ApiParam(value = "",required=true) @PathVariable("id") String id,@ApiParam(value = "") @Valid @RequestParam(value = "width", required = false) Integer width,@ApiParam(value = "") @Valid @RequestParam(value = "height", required = false) Integer height) {
        String accept = request.getHeader("Accept");
        QRItem qr = new QRItem();
        qr.setUri("https://www.google.es");
        qr.setQr("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAGQAQMAAAC6caSPAAAABlBMVEX///8AAABVwtN+AAABwElEQVR4nO3bPZLCMAyGYTNbUHKEHGWPBkfLUThCSgoGrSVbxs6ys5SJeL+G/PihQkQ2JiVCYuRLutzt0rdckjzscJI5nfsRFwgkELl5HRwyOcpipL7ZNb+exQfUOxBIHHKyUjDiyfVSKknrJd8R8xBIYHK0w/ywkOYhkM8gKZ/MOrCcTG+VGASyDVLjH/6hzZ/f6JQgkJ2SLs+e39qe21tzZAhkp+RX6pOjZCpPjn8CgeySPOul9kMv5ri5eLSO3EMgMYjXRc6jntRRfn1Y9IdANkuO44d/WLTvslQPgUQh5ca11MFz9tt6/q5exgkvBLJ3kqyj0bO21GmjNDe7Xg/LXAACiUO0Xpa+Uzp5PzTUy3qaAIFEIFoVeiar3FsPZek7JQgkChlWN8vzYawXqR3UNwSybXLNRyKt7ekW7Wt8UIJAwpBV2s+1/s0/61VdB1peAwhkt+TFXoXko3yvQj5ZT3ghkAhk2KXWrfb4Fs1WLxMEEo6sdtrrKHmMlbSuFwhk+6TcsLRvfg8EEprY/mL/hbZGr//xzxQIZMek5tB6/nNZ6vQSsZfymy4EEol08b+ZdIv+mm4EBBKHEPKh+QEBLQhcYvmo3AAAAABJRU5ErkJggg==");
        return new ResponseEntity<QRItem>(qr, HttpStatus.OK);
    }

}
