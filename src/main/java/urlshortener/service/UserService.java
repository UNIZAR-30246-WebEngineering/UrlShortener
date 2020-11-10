package urlshortener.service;

import org.springframework.stereotype.Service;
import urlshortener.domain.User;
import urlshortener.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(String username, String password) {
    User u = UserBuilder.newInstance()
        .username(username)
        .password(password)
        .build();

    return userRepository.save(u);
  }
}
