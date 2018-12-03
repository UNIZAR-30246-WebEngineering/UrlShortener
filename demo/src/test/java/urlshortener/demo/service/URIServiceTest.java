package urlshortener.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URIItem;


@RunWith(SpringRunner.class)
@SpringBootTest
public class URIServiceTest extends BaseServiceTest{

    @Autowired
    private URIService service;
    private URIItem item1, item2, item3;

    public URIServiceTest(){
        this.item1 = (URIItem) new URIItem().id("abc").redirection("http://google.es").hashpass("none");
        this.item2 = (URIItem) new URIItem().id("abc").redirection("http://google.com").hashpass("none");
        this.item3 = (URIItem) new URIItem().id("abcd").redirection("http://google.es").hashpass("none");
    }

    @Test
    public void cleanUp() {
        super.cleanUp(service);
    }

    @Test
    public void testInsertOK() {
        super.testInsertOK(service, item1);
    }

    @Test
    public void testInsertDuplicateFail() {
        super.testInsertDuplicateFail(service, item1, item2);
    }

    @Test
    public void testGet() {
        super.testGet(service, item1, item2, item3);
    }

    @Test
    public void testRemove() {
        super.testRemove(service, item1, item2, item3);
    }
}
