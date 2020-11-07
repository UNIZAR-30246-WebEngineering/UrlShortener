package urlshortener.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

  public ShortURL save(String url, String sponsor, String ip) {
    ShortURL su = ShortURLBuilder.newInstance()
        .target(url)
        .uri((String hash) -> linkTo(methodOn(UrlShortenerController.class).redirectTo(hash, null))
            .toUri())
        .sponsor(sponsor)
        .createdNow()
        .randomOwner()
        .temporaryRedirect()
        .treatAsSafe()
        .ip(ip)
        .unknownCountry()
        .build();
    return shortURLRepository.save(su);
  }

  public List<ShortURL> findByTarget(String target) {
    return shortURLRepository.findByTarget(target);
  }

  public Long count(){
    return shortURLRepository.count();
  }

  public void setAlcanzable(String hash, Integer alcanzable){
    shortURLRepository.setAlcanzableByHash(hash, alcanzable);
  }
}
