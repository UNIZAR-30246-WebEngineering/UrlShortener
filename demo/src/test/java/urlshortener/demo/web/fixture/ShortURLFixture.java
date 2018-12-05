package urlshortener.demo.web.fixture;

import urlshortener.demo.domain.ShortURL;

public class ShortURLFixture {

	public static ShortURL someUrl() {
		return new ShortURL().hash("someKey").target("http://example.com/").mode(307).safe(true);
	}
}
