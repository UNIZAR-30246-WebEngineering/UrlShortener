package urlshortener.demo.repository;

import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

public interface IRepository<K, V extends BaseEntity<K>> {

    void add(V value) throws CannotAddEntityException;

    V get(K key) throws UnknownEntityException;

    void remove(K key) throws UnknownEntityException;

    void removeAll();

    long getNextID();

    boolean contains(K key);
}
