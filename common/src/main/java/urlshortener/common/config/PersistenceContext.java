package urlshortener.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "urlshortener.common.repository", "io.swagger", "io.swagger.api" , "io.swagger.configuration" })
public class PersistenceContext {

}
