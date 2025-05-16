package com.babel.employee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "API of employers", version = "1.0",
        description = "Employer microservice in spring boot 3",
        contact = @Contact(
                name = "Eulices Martinez Santamaria",
                email = "eulices.santamaria@gmail.com"
        )),
        servers = {
                @Server(
                        description = "API DEV",
                        url = "http://localhost:8080"
                )
        }
)
@Configuration
public class SwaggerConfig {
}
