package urlshortener.demo.repository;

import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

public interface IRepository<K, V extends BaseEntity<K>> {

    void add(V value);

    V get(K key);

    void remove(K key);

    void removeAll();
}
