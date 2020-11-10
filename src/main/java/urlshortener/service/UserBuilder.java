package urlshortener.service;

import org.springframework.http.HttpStatus;
import urlshortener.domain.ShortURL;
import urlshortener.domain.User;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.UUID;
import java.util.function.Function;

import static com.google.common.hash.Hashing.murmur3_32;

public class UserBuilder {

  private String username;
  private String password;

  static UserBuilder newInstance() {
    return new UserBuilder();
  }

  User build() {
    return new User(
        username,
        password
    );
  }

  UserBuilder username(String username) {
    this.username = username;
    return this;
  }

  UserBuilder password(String password) {
    this.password = password;
    return this;
  }

}
