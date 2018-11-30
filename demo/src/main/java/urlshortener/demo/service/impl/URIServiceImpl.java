package urlshortener.demo.service.impl;

import org.springframework.stereotype.Service;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.service.AbstractService;
import urlshortener.demo.service.URIService;

@Service
public class URIServiceImpl extends AbstractService<String, URIItem> implements URIService {
}
