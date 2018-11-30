package urlshortener.demo.service.impl;

import org.springframework.stereotype.Service;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.service.AbstractService;
import urlshortener.demo.service.QRService;

@Service
public class QRServiceImpl extends AbstractService<String, QRItem> implements QRService {
}
