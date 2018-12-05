package urlshortener.demo.repository.impl;

import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.QRRepository;

@Repository
public class QRRepositoryImpl extends AbstractRepository<String, QRItem> implements QRRepository {
}
