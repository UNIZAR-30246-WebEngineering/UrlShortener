package urlshortener.demo.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckAlive {

    //@org.springframework.beans.factory.annotation.Autowired
    public CheckAlive() {
    }

    public int makeRequest(String s) throws IOException {
        URL url = new URL(s);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        return con.getResponseCode();
    }

}