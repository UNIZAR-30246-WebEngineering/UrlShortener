package urlshortener.demo.repository;

import urlshortener.demo.domain.BaseEntity;

public interface IRepository<K, V extends BaseEntity<K>> {

    void add(V value);

    V get(K key);

    void remove(K key);

    void removeAll();

    long getNextID();

    boolean contains(K key);
}
