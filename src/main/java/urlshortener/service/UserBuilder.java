package urlshortener.service;

import urlshortener.domain.User;

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
