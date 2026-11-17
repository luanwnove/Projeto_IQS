package br.edu.fatec.iqs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI documentacaoApi() {
        return new OpenAPI().info(new Info()
                .title("API de Pagamentos - IQS")
                .version("1.0.0")
                .description("Exemplo de documentação REST com Springdoc OpenAPI"));
    }
}
