package urlshortener.demo.repository.impl;

import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.URIRepository;

@Repository
public class URIRepositoryImpl extends AbstractRepository<String, URIItem> implements URIRepository {
}
