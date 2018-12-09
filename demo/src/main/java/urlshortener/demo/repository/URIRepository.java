package urlshortener.demo.repository;

import urlshortener.demo.domain.URIItem;

public interface URIRepository extends IRepository<String, URIItem> {

    long getRedirectionAmount(String hash, long timeFromNow);

}
