package urlshortener.demo.service;

import urlshortener.demo.domain.QRItem;

public class QRServiceTest extends BaseServiceTest<String, QRItem, QRService> {

    public QRServiceTest() {
        super(new QRItem().uri("abc").qr("randomString"),
                new QRItem().uri("abc").qr("randomString2"),
                new QRItem().uri("abcd").qr("randomString3"));
    }
}
