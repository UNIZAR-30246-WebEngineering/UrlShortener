package urlshortener.demo.repository.fixture;

import urlshortener.demo.domain.ShortURL;

public class ShortURLFixture {

	public static ShortURL url1() {
		return new ShortURL().hash("1").target("http://www.unizar.es/").safe(false);
	}

	public static ShortURL url1modified() {
		return new ShortURL().hash("1").target("http://www.unizar.org/").safe(false);
	}

	public static ShortURL url2() {
		return new ShortURL().hash("2").target("http://www.unizar.es/").safe(false);
	}

	public static ShortURL url3() {
		return new ShortURL().hash("3").target("http://www.google.es").safe(false);
	}

	public static ShortURL badUrl() {
		return new ShortURL();
	}

	public static ShortURL urlSponsor() {
		return new ShortURL().hash("3").sponsor("sponsor").safe(false);
	}

	public static ShortURL urlSafe() {
		return new ShortURL().hash("4").sponsor("sponsor").safe(true);
	}
}
