package urlshortener.demo.repository.fixture;

import urlshortener.demo.domain.Click;
import urlshortener.demo.domain.ShortURL;

public class ClickFixture {

	public static Click click(ShortURL su) {
		return new Click().hash(su.getHash());
	}
}
