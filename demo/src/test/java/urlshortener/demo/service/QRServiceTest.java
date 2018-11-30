package urlshortener.demo.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.QRItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QRServiceTest extends BaseServiceTest<String, QRItem, QRService> {

    public QRServiceTest() {
        super(new QRItem().uri("abc").qr("randomString"),
                new QRItem().uri("abc").qr("randomString2"),
                new QRItem().uri("abcd").qr("randomString3"));
    }
}
