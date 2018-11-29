package urlshortener.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(basePackages = { "urlshortener.common" })
@Import({PersistenceContext.class})
public class TestConfiguration {

}
