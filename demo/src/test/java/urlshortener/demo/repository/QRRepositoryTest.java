package urlshortener.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.QRItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QRRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private QRRepository repository;
    private QRItem item1, item2, item3;

    public QRRepositoryTest() {
        this.item1 = new QRItem().uri("abc").qr("randomString");
        this.item2 = new QRItem().uri("abc").qr("randomString2");
        this.item3 = new QRItem().uri("abcd").qr("randomString3");
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
