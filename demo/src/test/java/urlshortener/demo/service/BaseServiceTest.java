package urlshortener.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public abstract class BaseServiceTest<K, V extends BaseEntity<K>, T extends IService<K, V>> {

    @Autowired
    private T service;
    private V item1, item2, item3;

    protected void construct(V item1, V item2, V item3){
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    @Before
    public void cleanUp(){
        service.removeAll();
    }

    @Test
    public void testInsertOK(){
        try {
            service.add(item1);
        } catch (CannotAddEntityException e) {
            fail();
        }

        try {
            assertEquals(item1, service.get(item1.getId()));
        } catch (UnknownEntityException e) {
            fail();
        }
    }

    @Test
    public void testInsertDuplicateFail(){
        try {
            service.add(item1);
        } catch (CannotAddEntityException ignored) { }

        try {
            service.add(item1);
            fail();
        } catch (CannotAddEntityException ignored) { }
        try {
            service.add(item2);
            fail();
        } catch (CannotAddEntityException ignored) { }
    }

    @Test
    public void testGet(){
        try {
            service.add(item1);
        } catch (CannotAddEntityException ignored) { }

        try {
            assertEquals(item1, service.get(item1.getId()));
        } catch (UnknownEntityException ignored) { fail(); }
        try {
            assertNotEquals(item2, service.get(item2.getId()));
        } catch (UnknownEntityException ignored) { fail(); }

        try {
            service.get(item3.getId());
            fail();
        } catch (UnknownEntityException ignored) { }
    }

    @Test
    public void testRemove(){
        try {
            service.add(item1);
            service.add(item3);
        } catch (CannotAddEntityException ignored) { }

        try {
            service.remove(item1.getId());
        } catch (UnknownEntityException e) {
            fail();
        }

        try {
            service.remove(item2.getId());
            fail();
        } catch (UnknownEntityException ignored) {
        }
    }

}
