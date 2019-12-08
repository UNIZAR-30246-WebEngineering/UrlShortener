package urlshortener.service;

import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class to check if a URI is reachable.
 */
@Service
public class URIAvailable {

    // List of error codes admited by the checker
    private static final int[] errorCodesOK= {200, 201, 204};

    // Time between check and check
    private static final int TIME_URI_CHECK = 5000;

    // Timeout get petition
    private static final int TIME_GET = 1000;

    private ConcurrentHashMap<String, AtomicBoolean> map = new ConcurrentHashMap<>();

    /**
     * Update the booleans of the hashmap
     */
    @Scheduled(fixedRate = TIME_URI_CHECK)
    public void checkUris(){
        map.forEach((uri, state) -> state.set(checkUriAvailable(uri)));
    }

    /**
     * Save the uri in the hashmap for check
     * @param uri
     */
    public void saveURI(String uri){
        if(!map.containsKey(uri)){
            map.put(uri, new AtomicBoolean(checkUriAvailable(uri)));
        }
    }

    /**
     * Check if the uri is available, searching in the hashmap or get petition
     * @param uri
     * @return
     */
    public boolean isURIAvailable(String uri){
        boolean isAvailable = false;
        if(map.containsKey(uri)){
            isAvailable =  map.get(uri).get();
        }else{
            isAvailable = checkUriAvailable(uri);
        }
        return isAvailable;
    }

    /**
     * Returns true if and only if the uri is reachable, false in other cases.
     * @param uri The uri to check
     * @return True if the uri is reachable, false in other cases.
     */
    private boolean checkUriAvailable(@NonNull String uri){
        int response = getURIResponseGet(uri);
        for (int value : errorCodesOK) {
            if (response == value) {
                return true;
            }
        }
        // Generate a error because uri is not available
        return false;
    }

    /**
     * Make a get request to the uri and check if it is reachable or not.
     * If it is reachable, it returns 1. Otherwise it returns -1.
     * @param uri The uri to check if it is reachable
     * @return 1 if is reachable, -1 if not.
     */
    private static int getURIResponseGet(@NonNull String uri){
        try{
            // First we create and open the http connection
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(uri).openConnection();
            // We make a request only to get the header
            httpConnection.setRequestMethod("HEAD");
            // Connect Timeout
            httpConnection.setConnectTimeout(TIME_GET);
            // Read Timeout
            httpConnection.setReadTimeout(TIME_GET);
            // We return the code that has arrived from the request.
            return httpConnection.getResponseCode();
        }catch(Exception e){
            // In case of error, we return -1
            return -1;
        }
    }
}