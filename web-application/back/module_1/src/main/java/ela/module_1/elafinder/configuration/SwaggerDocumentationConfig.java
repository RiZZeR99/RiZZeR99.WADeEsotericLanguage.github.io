package ela.module_1.elafinder.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("ELA SPARQL Endpoint - OpenAPI 3.0")
                        .description("This document is for describing the functionalities provided by the ELA SPARQL component.")
                        .termsOfService("")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("")));
    }

}
