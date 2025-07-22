package com.notfound.lpickbackend.common.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://lpick.duckdns.org"))
                .addSecurityItem(new SecurityRequirement().addList("kakao"))
                .addSecurityItem(new SecurityRequirement().addList("cookieAuth")) // ✅ 쿠키 인증 방식 추가
                .components(new Components()
                        // ✅ 기존 Kakao OAuth 설정
                        .addSecuritySchemes("kakao", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("https://kauth.kakao.com/oauth/authorize")
                                                .tokenUrl("https://kauth.kakao.com/oauth/token")
                                                .scopes(new Scopes()
                                                        .addString("profile_nickname", "Access to nickname")
                                                        .addString("profile_image", "Access to profile image")
                                                )
                                        )
                                )
                        )
                        // ✅ 쿠키 기반 인증 스키마 추가 (access_token 기준)
                        .addSecuritySchemes("cookieAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.COOKIE)
                                .name("access_token") //
                        )
                );
    }
}
