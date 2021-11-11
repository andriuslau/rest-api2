package com.juniorjavadeveloper.restapidemo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIConfiguration() {
        return new OpenAPI()
                .components(new Components())
                .info(appInfo());
    }

    private Contact contactInfo() {
        return new Contact()
                .name("Andrius")
                .email("andrius@juniorjavadeveloper.com")
                .url("http://www.juniorjavadeveloper.com");
    }

    private Info appInfo() {
        return new Info()
                .title("From Zero to Junior")
                .description("Simple Spring Boot Rest API application - from Zero to Junior.")
                .version("0.1")
                .contact(contactInfo());
    }
}
