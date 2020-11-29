package urlshortener.service;

import com.jayway.jsonpath.JsonPath;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import urlshortener.domain.safebrowsing.*;

@Service
public class SafeBrowsingService {

  private final String API_KEY = System.getenv("GSB_API_KEY");

  public boolean isSafe(String url) {
    // Create request through domain classes
    SBClient sbClient = new SBClient("urly", "1.0");
    List<String> threatTypes = Arrays.asList("MALWARE", "SOCIAL_ENGINEERING", "UNWANTED_SOFTWARE");
    List<String> platformTypes = Collections.singletonList("ALL_PLATFORMS");
    List<String> threatEntryTypes = Collections.singletonList("URL");
    SBThreatEntry threatEntry = new SBThreatEntry(url);
    List<SBThreatEntry> threatEntries = Collections.singletonList(threatEntry);
    SBThreatInfo sbThreatInfo =
        new SBThreatInfo(threatTypes, platformTypes, threatEntryTypes, threatEntries);
    SBRequest sbRequest = new SBRequest(sbClient, sbThreatInfo);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Send request to Google Safe Browsing API (Lookup API)
    HttpEntity<SBRequest> request = new HttpEntity<>(sbRequest, headers);
    RestTemplate restTemplate = new RestTemplate();
    String sbUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=" + API_KEY;
    ResponseEntity<String> response = restTemplate.postForEntity(sbUrl, request, String.class);

    // Decide if it's safe
    if (response.getStatusCode() != HttpStatus.OK) {
      throw new HttpServerErrorException(response.getStatusCode());
    } else {
      String json = response.getBody();
      List<String> matches = JsonPath.parse(json).read("$..matches");
      return matches.isEmpty();
    }
  }
}
