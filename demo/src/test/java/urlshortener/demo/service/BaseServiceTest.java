package urlshortener.demo.service;

import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

import static org.junit.Assert.*;

public abstract class BaseServiceTest {

    protected void cleanUp(IService service){
        service.removeAll();
    }

    protected <K, V extends BaseEntity<K>> void testInsertOK(IService<K, V> service, V item1){
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

    protected <K, V extends BaseEntity<K>> void testInsertDuplicateFail(IService<K, V> service, V item1, V item2){
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

    protected <K, V extends BaseEntity<K>> void testGet(IService<K, V> service, V item1, V item2, V item3){
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


    protected <K, V extends BaseEntity<K>> void testRemove(IService<K, V> service, V item1, V item2, V item3){
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
