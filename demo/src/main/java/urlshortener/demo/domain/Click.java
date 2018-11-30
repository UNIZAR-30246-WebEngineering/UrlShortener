package urlshortener.demo.domain;

import java.sql.Date;

public class Click {

	private Long id;
	private String hash;
	private Date created;
	private String referrer;
	private String browser;
	private String platform;
	private String ip;
	private String country;


	public Long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public Date getCreated() {
		return created;
	}

	public String getReferrer() {
		return referrer;
	}

	public String getBrowser() {
		return browser;
	}

	public String getPlatform() {
		return platform;
	}

	public String getIp() {
		return ip;
	}

	public String getCountry() {
		return country;
	}

	public Click id(Long id) {
		this.id = id;
        return this;
	}

	public Click hash(String hash) {
		this.hash = hash;
        return this;
	}

	public Click created(Date created) {
		this.created = created;
        return this;
	}

	public Click referrer(String referrer) {
		this.referrer = referrer;
        return this;
	}

	public Click browser(String browser) {
		this.browser = browser;
        return this;
	}

	public Click platform(String platform) {
		this.platform = platform;
        return this;
	}

	public Click ip(String ip) {
		this.ip = ip;
        return this;
	}

	public Click country(String country) {
		this.country = country;
		return this;
	}
}
