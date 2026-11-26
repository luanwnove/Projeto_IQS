package br.edu.fatec.iqs.produtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
@Transactional
class ProdutoPostgresContainerScenario {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configurarBanco(DynamicPropertyRegistry propriedades) {
        propriedades.add("spring.datasource.url", postgres::getJdbcUrl);
        propriedades.add("spring.datasource.username", postgres::getUsername);
        propriedades.add("spring.datasource.password", postgres::getPassword);
        propriedades.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private ProdutoRepository repository;

    @Test
    void devePersistirProdutoNoPostgreSQL() {
        Produto salvo = repository.saveAndFlush(new Produto("Notebook", new BigDecimal("3500.00")));
        assertTrue(repository.findById(salvo.getId()).isPresent());
        assertEquals("Notebook", repository.findById(salvo.getId()).orElseThrow().getNome());
    }
}
