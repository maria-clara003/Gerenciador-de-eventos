package com.github.henriquefdesouza.trabalhofaculdade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5500") // Permite o frontend rodando no localhost:5500
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("Authorization", "Content-Type", "*")
                .allowCredentials(true)
                .maxAge(3600); // Configuração do tempo de cache para CORS
    }
}