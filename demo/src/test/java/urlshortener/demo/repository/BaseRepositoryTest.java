package urlshortener.demo.repository;

import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

import static org.junit.Assert.*;

public abstract class BaseRepositoryTest {

    protected void cleanUp(IRepository service){
        service.removeAll();
    }

    protected <K, V extends BaseEntity<K>> void testInsertOK(IRepository<K, V> service, V item1){
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

    protected <K, V extends BaseEntity<K>> void testInsertDuplicateFail(IRepository<K, V> service, V item1, V item2){
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

    protected <K, V extends BaseEntity<K>> void testGet(IRepository<K, V> service, V item1, V item2, V item3){
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


    protected <K, V extends BaseEntity<K>> void testRemove(IRepository<K, V> service, V item1, V item2, V item3){
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

    protected <K, V extends BaseEntity<K>> void testContains(IRepository<K, V> service, K item1){
        assertTrue(service.contains(item1));
    }

    protected <K, V extends BaseEntity<K>> void testNotContains(IRepository<K, V> service, K item1){
        assertFalse(service.contains(item1));
    }

}
