package urlshortener.demo.repository.impl;

import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.URIRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class URIRepositoryImpl extends AbstractRepository<String, URIItem> implements URIRepository {

    @Override
    public List<URIItem> comprobar() {
        return new ArrayList<>();
    }
}
