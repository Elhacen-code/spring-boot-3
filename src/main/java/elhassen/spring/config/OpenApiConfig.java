package elhassen.spring.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springSecurityOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Security API")
                        .description("Authentication and authorization endpoints")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact().name("Support").email("support@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://example.com/repo"));
    }
}

