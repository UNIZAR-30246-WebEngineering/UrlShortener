package urlshortener.demo.repository;

import org.springframework.http.HttpStatus;
import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

import java.util.HashMap;
import java.util.Map;

public class AbstractRepository<K, V extends BaseEntity<K>> implements IRepository<K, V> {

    private long nextID = 0;
    private Map<K, V> uris = new HashMap<>();

    @Override
    public void add(V uri) {
        if(uris.containsKey(uri.getId())){
            throw new CannotAddEntityException(HttpStatus.BAD_REQUEST.value(), "Cannot add uri with hash " + uri.getId());
        }

        uris.put(uri.getId(), uri);
        nextID++;
    }

    @Override
    public V get(K hash) {
        V item = uris.get(hash);
        if(item == null){
            throw new UnknownEntityException(HttpStatus.NOT_FOUND.value(), "Cannot fetch uri with hash " + hash);
        }
        return item;
    }

    @Override
    public void remove(K hash) {
        if(!uris.containsKey(hash)){
            throw new UnknownEntityException(HttpStatus.NOT_FOUND.value(), "Cannot fetch uri with hash " + hash);
        }
        uris.remove(hash);
    }

    @Override
    public void removeAll() {
        uris.clear();
    }

    @Override
    public long getNextID(){
        return nextID;
    }

    @Override
    public boolean contains(K key){
        return uris.containsKey(key);
    }

}
