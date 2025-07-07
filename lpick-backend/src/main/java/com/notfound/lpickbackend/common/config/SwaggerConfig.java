package com.notfound.lpickbackend.common.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("kakao"))
                .components(new Components()
                        .addSecuritySchemes("kakao", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("https://kauth.kakao.com/oauth/authorize")
                                                .tokenUrl("https://kauth.kakao.com/oauth/token")
                                                .scopes(new Scopes()
                                                        .addString("profile_nickname", "Access to nickname")
                                                        .addString("profile_image", "Access to profile image"))))));
    }
}
