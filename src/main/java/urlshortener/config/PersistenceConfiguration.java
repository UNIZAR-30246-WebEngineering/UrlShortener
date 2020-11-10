package urlshortener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import urlshortener.repository.ClickRepository;
import urlshortener.repository.ShortURLRepository;
import urlshortener.repository.UserRepository;
import urlshortener.repository.impl.ClickRepositoryImpl;
import urlshortener.repository.impl.ShortURLRepositoryImpl;
import urlshortener.repository.impl.UserRepositoryImpl;

@Configuration
public class PersistenceConfiguration {

  private final JdbcTemplate jdbc;

  public PersistenceConfiguration(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Bean
  ShortURLRepository shortURLRepository() {
    return new ShortURLRepositoryImpl(jdbc);
  }

  @Bean
  ClickRepository clickRepository() {
    return new ClickRepositoryImpl(jdbc);
  }

  @Bean
  UserRepository userRepository() {
    return new UserRepositoryImpl(jdbc);
  }

}
