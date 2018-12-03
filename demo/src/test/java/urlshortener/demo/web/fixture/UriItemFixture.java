package urlshortener.demo.web.fixture;

import urlshortener.demo.domain.URIItem;

public class UriItemFixture {

    public static URIItem someURI(){
        return (URIItem) new URIItem().id("abc").redirection("https://google.es").hashpass("abc");
    }

}
