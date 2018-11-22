package urlshortener.common.config;

import io.swagger.configuration.JacksonConfiguration;
import io.swagger.configuration.SwaggerDocumentationConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "urlshortener.common.repository", "io.swagger", "io.swagger.api" , "io.swagger.configuration" })
@Import({JacksonConfiguration.class, SwaggerDocumentationConfig.class})
public class PersistenceContext {

}
