package urlshortener.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "urlshortener.common.repository" })
@Import({JacksonConfiguration.class, SwaggerDocumentationConfig.class})
public class PersistenceContext {

}
