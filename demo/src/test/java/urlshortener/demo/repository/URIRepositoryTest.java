package urlshortener.demo.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URIItem;

import static org.junit.Assert.assertEquals;


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

    @Before
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
        repository.add(item1);
        super.testContains(repository, item1.getId());
        super.testNotContains(repository, "randomID :D");
    }

    @Test
    public void testRedirectionAmount(){
        repository.add(item1);
        assertEquals(0, repository.getRedirectionAmount(item1.getId(), System.currentTimeMillis()));

        repository.get(item1.getId());
        assertEquals(1, repository.getRedirectionAmount(item1.getId(), System.currentTimeMillis()));
        assertEquals(0, repository.getRedirectionAmount(item1.getId(), -1000));
    }
}
