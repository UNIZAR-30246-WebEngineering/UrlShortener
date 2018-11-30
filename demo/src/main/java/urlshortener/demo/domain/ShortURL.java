package urlshortener.demo.domain;

import java.net.URI;
import java.sql.Date;

public class ShortURL {

	private String hash;
	private String target;
	private URI uri;
	private String sponsor;
	private Date created;
	private String owner;
	private Integer mode;
	private Boolean safe;
	private String ip;
	private String country;

	public String getHash() {
		return hash;
	}

	public String getTarget() {
		return target;
	}

	public URI getUri() {
		return uri;
	}

	public Date getCreated() {
		return created;
	}

	public String getOwner() {
		return owner;
	}

	public Integer getMode() {
		return mode;
	}

	public String getSponsor() {
		return sponsor;
	}

	public Boolean getSafe() {
		return safe;
	}

	public String getIP() {
		return ip;
	}

	public String getCountry() {
		return country;
	}

	public ShortURL hash(String hash) {
		this.hash = hash;
		return this;
	}

	public ShortURL target(String target) {
		this.target = target;
		return this;
	}

	public ShortURL uri(URI uri) {
		this.uri = uri;
		return this;
	}

	public ShortURL sponsor(String sponsor) {
		this.sponsor = sponsor;
		return this;
	}

	public ShortURL created(Date created) {
		this.created = created;
		return this;
	}

	public ShortURL owner(String owner) {
		this.owner = owner;
		return this;
	}

	public ShortURL mode(Integer mode) {
		this.mode = mode;
		return this;
	}

	public ShortURL safe(Boolean safe) {
		this.safe = safe;
		return this;
	}

	public ShortURL ip(String ip) {
		this.ip = ip;
		return this;
	}

	public ShortURL country(String country) {
		this.country = country;
		return this;
	}
}
