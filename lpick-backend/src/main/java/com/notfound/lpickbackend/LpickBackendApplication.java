package com.notfound.lpickbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//@OpenAPIDefinition(servers = {
//        @Server(url = "https://lpick.duckdns.org", description = "Default Server URL")}
//)
@SpringBootApplication
public class LpickBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LpickBackendApplication.class, args);
    }

}
