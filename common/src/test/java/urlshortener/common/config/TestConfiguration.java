package urlshortener.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(basePackages = { "urlshortener.common", "io.swagger", "io.swagger.api" , "io.swagger.configuration" })
@Import({PersistenceContext.class})
public class TestConfiguration {

}
