package urlshortener.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import urlshortener.domain.ShortURL;
import urlshortener.repository.ShortURLRepository;
import urlshortener.web.UrlShortenerController;

import java.util.List;

@Service
public class ShortURLService {

  private final ShortURLRepository shortURLRepository;

  public ShortURLService(ShortURLRepository shortURLRepository) {
    this.shortURLRepository = shortURLRepository;
  }

  public ShortURL findByKey(String id) {
    return shortURLRepository.findByKey(id);
  }

  public JSONObject findByUser(String userId) {

     return toJson(shortURLRepository.findByUser(userId));
  }

  private JSONObject toJson(List<ShortURL> shortList) {
    JSONObject jObject = new JSONObject();

    try
    {
      JSONArray jArray = new JSONArray();
      for (ShortURL su : shortList)
      {
        JSONObject shortJSON = new JSONObject();
        shortJSON.put( "uri", "http://" + UrlShortenerController.HOST + "/" + su.getHash());
        shortJSON.put("target", su.getTarget());
        shortJSON.put("clicks", su.getClicks());
        jArray.add(shortJSON);
      }
      jObject.put("urlList", jArray);
      return jObject;
    } catch (Exception e) {
      return null;
    }

  }

  public boolean isExpired(String id){
    return shortURLRepository.isExpired(id);
  }

  public void delete(String id){
    shortURLRepository.delete(id);
  }

  public ShortURL save(String url, String sponsor, String owner, String ip) {
    ShortURL su = ShortURLBuilder.newInstance()
        .target(url)
        .uri((String hash) -> linkTo(methodOn(UrlShortenerController.class).redirectTo(hash, null)).toUri())
        .sponsor(sponsor)
        .createdNow()
        .owner(owner)
        .temporaryRedirect()
        .treatAsSafe()
        .ip(ip)
        .unknownCountry()
        .build();
    return shortURLRepository.save(su);
  }

  public boolean isValidated(String id) {
    return findByKey(id) != null && findByKey(id).isValidated();
  }
}
