package urlshortener.domain;

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

  public Click(Long id, String hash, Date created, String referrer,
               String browser, String platform, String ip, String country) {
    this.id = id;
    this.hash = hash;
    this.created = created;
    this.referrer = referrer;
    this.browser = browser;
    this.platform = platform;
    this.ip = ip;
    this.country = country;
  }

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

  public void setId(Long id) {
    this.id = id;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public void setCreated(java.sql.Date created) {
    this.created = created;
  }

  public void setReferrer(String referrer) {
    this.referrer = referrer;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
