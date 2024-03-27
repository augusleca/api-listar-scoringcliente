package com.bcba.nosisensolnet.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOriginPatterns("*")
            //.allowedOrigins("") // TODO: cambiar esto por los dominios permitidos
            .allowedMethods("GET") // se pueden agregar POST, DELETE, etc, pero no es necesario en esta API
            .allowCredentials(true);
    }
}

