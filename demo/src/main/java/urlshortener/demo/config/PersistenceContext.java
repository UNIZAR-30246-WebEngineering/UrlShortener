package urlshortener.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import urlshortener.demo.repository.ClickRepository;
import urlshortener.demo.repository.ClickRepositoryImpl;
import urlshortener.demo.repository.ShortURLRepository;
import urlshortener.demo.repository.ShortURLRepositoryImpl;

@Configuration
@ComponentScan(basePackages = {"urlshortener.demo" })
@Import({JacksonConfiguration.class, SwaggerDocumentationConfig.class})
public class PersistenceContext {

	@Autowired
    protected JdbcTemplate jdbc;

	@Bean
	ShortURLRepository shortURLRepository() {
		return new ShortURLRepositoryImpl(jdbc);
	}
 	
	@Bean
	ClickRepository clickRepository() {
		return new ClickRepositoryImpl(jdbc);
	}
	
}
