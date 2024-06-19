package com.exampleAPI.olympicGames.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Clase que permite configurar el encabezado de la página de Swagger con la documentación del API
@Configuration
public class OlympicGamesConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Olympic Games API")
                        .description("API that shows the Olympic Games medal table of all the Olympic Games")
                        .version("1.0"));
    }

}