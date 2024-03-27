package com.bcba.nosisensolnet.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(
    info = @Info(
        title = "api-listar-scoringcliente",
        version = "v1",
        description = "Permite obtener el scoring de un cliente. Además, se compone de elementos varios de resiliencia y aplica validaciones."
    )
)
public class SpringdocConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("nosisensolnet")
            .pathsToMatch("/api/**")
            .build();
    }
}
