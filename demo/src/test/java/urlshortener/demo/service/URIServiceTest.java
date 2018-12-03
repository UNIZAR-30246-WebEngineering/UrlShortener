package urlshortener.demo.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URIItem;


@RunWith(SpringRunner.class)
@SpringBootTest
public class URIServiceTest extends BaseServiceTest<String, URIItem, URIService>{

    public URIServiceTest(){
        super((URIItem) new URIItem().id("abc").redirection("http://google.es").hashpass("none"),
                (URIItem) new URIItem().id("abc").redirection("http://google.com").hashpass("none"),
                (URIItem) new URIItem().id("abcd").redirection("http://google.es").hashpass("none"));
    }

}
