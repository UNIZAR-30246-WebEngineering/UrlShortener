package urlshortener.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URIItem;


@RunWith(SpringRunner.class)
@SpringBootTest
public class URIRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private URIRepository repository;
    private URIItem item1, item2, item3;

    public URIRepositoryTest(){
        this.item1 = (URIItem) new URIItem().id("abc").redirection("http://google.es").hashpass("none");
        this.item2 = (URIItem) new URIItem().id("abc").redirection("http://google.com").hashpass("none");
        this.item3 = (URIItem) new URIItem().id("abcd").redirection("http://google.es").hashpass("none");
    }

    @Test
    public void cleanUp() {
        super.cleanUp(repository);
    }

    @Test
    public void testInsertOK() {
        super.testInsertOK(repository, item1);
    }

    @Test
    public void testInsertDuplicateFail() {
        super.testInsertDuplicateFail(repository, item1, item2);
    }

    @Test
    public void testGet() {
        super.testGet(repository, item1, item2, item3);
    }

    @Test
    public void testRemove() {
        super.testRemove(repository, item1, item2, item3);
    }

    @Test
    public void testContains(){
        super.testContains(repository, item1.getId());
        super.testNotContains(repository, "randomID :D");
    }
}
