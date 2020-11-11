package urlshortener.web;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import urlshortener.domain.ShortURL;
import urlshortener.domain.User;
import urlshortener.service.ClickService;
import urlshortener.service.ShortURLService;
import urlshortener.service.URLValidatorService;
import urlshortener.service.UserService;

@RestController
public class UrlShortenerController {
  public static final String HOST = "localhost";
  private final ShortURLService shortUrlService;
  private final ClickService clickService;
  private final UserService userService;

  private static final Logger log = LoggerFactory
          .getLogger(ClickService.class);

  public UrlShortenerController(ShortURLService shortUrlService, ClickService clickService, UserService userService) {
    this.shortUrlService = shortUrlService;
    this.clickService = clickService;
    this.userService = userService;
  }

  @RequestMapping(value = "/{id:(?!link|index).*}", method = RequestMethod.GET)
  public ResponseEntity<?> redirectTo(@PathVariable String id,
                                      HttpServletRequest request) {
    if(shortUrlService.isExpired(id)) {
      ShortURL l = shortUrlService.findByKey(id);
      shortUrlService.delete(l.getHash());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      ShortURL l = shortUrlService.findByKey(id);
      if (l != null) {
        clickService.saveClick(id, extractIP(request));
        return createSuccessfulRedirectToResponse(l);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<?> register(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
    log.debug("------------------------------------------");
    log.debug(username);
    log.debug(password);
    log.debug("------------------------------------------");
    if(username.equals("") || password.equals("")){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    User u = userService.save(username, password);
    if (u != null) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("uuid", u.getId());
      return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.IM_USED);
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<?> login(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
    if(username.equals("") || password.equals("")){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    User u = userService.login(username, password);
    if (u != null) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("uuid", u.getId());
      return new ResponseEntity<>(jsonObject, HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
  }

  @RequestMapping(value = "/link", method = RequestMethod.POST)
  public ResponseEntity<?> shortener(@RequestParam("url") String url,
                                            @RequestParam(value = "sponsor", required = false) String sponsor,
                                            @RequestParam("uuid") String userId,
                                            HttpServletRequest request) {

    URLValidatorService urlValidator = new URLValidatorService(url);
    if(!userService.exists(userId)){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    if (urlValidator.isValid()) {
      ShortURL su = shortUrlService.save(url, sponsor, userId, request.getRemoteAddr());
      HttpHeaders h = new HttpHeaders();
      h.setLocation(su.getUri());
      return new ResponseEntity<>(su, h, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/userlinks", method = RequestMethod.POST)
  public ResponseEntity<JSONObject> getUserLinks(@RequestParam("uuid") String userId,
                                                 HttpServletRequest request) {

    if(!userService.exists(userId)){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    JSONObject urlShort = shortUrlService.findByUser(userId);
    return new ResponseEntity<>(urlShort, HttpStatus.OK);

  }

  private String extractIP(HttpServletRequest request) {
    return request.getRemoteAddr();
  }

  private JSONObject createJSONResponse(String status, JSONObject jo) {
    JSONObject jsonResponse = new JSONObject();
    jsonResponse.put("status", status);
    if (jo != null) {
      jsonResponse.merge(jo);
    }

    return jsonResponse;
  }

  private ResponseEntity<?> createSuccessfulRedirectToResponse(ShortURL l) {
    HttpHeaders h = new HttpHeaders();
    h.setLocation(URI.create(l.getTarget()));
    return new ResponseEntity<>(h, HttpStatus.valueOf(l.getMode()));
  }
}
