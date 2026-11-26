package br.edu.fatec.iqs.produtos;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProdutoRepository repository;

    @Test
    void deveListarProdutosVazios() throws Exception {
        mvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void deveCriarProduto() throws Exception {
        mvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Smartphone Xiaomi\",\"preco\":1299.90}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.nome").value("Smartphone Xiaomi"))
                .andExpect(jsonPath("$.preco").value(1299.90));
    }

    @Test
    void deveBuscarProdutosPorNome() throws Exception {
        repository.save(new Produto("Smartphone Xiaomi", new BigDecimal("1299.90")));
        repository.save(new Produto("Notebook Dell", new BigDecimal("4500.00")));

        mvc.perform(get("/produtos/buscar").param("nome", "xiaomi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", containsString("Xiaomi")));
    }

    @Test
    void deveAtualizarEExcluirProduto() throws Exception {
        Long id = repository.save(new Produto("Teclado", new BigDecimal("150.00"))).getId();

        mvc.perform(put("/produtos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Mouse\",\"preco\":89.90}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mouse"));

        mvc.perform(delete("/produtos/{id}", id))
                .andExpect(status().isNoContent());

        mvc.perform(get("/produtos/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRejeitarCamposObrigatorios() throws Exception {
        mvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\",\"preco\":null}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarPrecoNegativo() throws Exception {
        mvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Produto\",\"preco\":-1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveDocumentarEndpointsDeProdutos() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/produtos'].get").exists())
                .andExpect(jsonPath("$.paths['/produtos/buscar'].get").exists())
                .andExpect(jsonPath("$.paths['/produtos'].post").exists());
    }
}
