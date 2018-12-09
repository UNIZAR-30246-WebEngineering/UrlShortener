package urlshortener.demo.repository.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.URIRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class URIRepositoryImpl extends AbstractRepository<String, URIItem> implements URIRepository {

    private Map<String, URIStats> stats = new HashMap<>();

    @Override
    public long getRedirectionAmount(String hash, long timeFromNow) {
        URIStats statsData = this.stats.get(hash);
        if(statsData == null) throw new UnknownEntityException(HttpStatus.BAD_REQUEST.value(), "Unknown URI " + hash);

        return statsData.getAccesssesAfter(System.currentTimeMillis() - timeFromNow);
    }

    @Override
    public void add(URIItem uri) throws CannotAddEntityException {
        stats.putIfAbsent(uri.getId(), new URIStats());
        super.add(uri);
    }

    @Override
    public URIItem get(String hash) throws UnknownEntityException {
        stats.putIfAbsent(hash, new URIStats());
        stats.get(hash).addAccess();

        return super.get(hash);
    }

    @Override
    public void remove(String hash) throws UnknownEntityException {
        stats.remove(hash);
        super.remove(hash);
    }

    @Override
    public void removeAll() {
        stats.clear();
        super.removeAll();
    }

    private static class URIStats{
        private List<Long> lastAccesses = new ArrayList<>();

        private void addAccess(){
            this.lastAccesses.add(System.currentTimeMillis());
        }

        private long getAccesssesAfter(long time){
            return lastAccesses.stream().filter(t -> t > time).count();
        }
    }
}
