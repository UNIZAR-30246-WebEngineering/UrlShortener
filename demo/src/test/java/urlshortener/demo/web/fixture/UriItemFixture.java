package urlshortener.demo.web.fixture;

import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;

public class UriItemFixture {

    public static URIItem someURI(){
        return (URIItem) new URIItem().id("abc").redirection("https://google.es").hashpass("abc");
    }

    public static URICreate someCorrectURICreate(){
        return new URICreate().uri("https://www.google.es");
    }

    public static URICreate someNullURICreate(){
        return new URICreate().uri(null);
    }

    public static URICreate someEmptyURICreate(){
        return new URICreate().uri("");
    }

}
