package br.edu.fatec.iqs.pagamentos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PagamentoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void registraPagamento() throws Exception {
        mvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Mensalidade\",\"valor\":150.00}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.descricao").value("Mensalidade"));
    }

    @Test
    void rejeitaPagamentoSemValor() throws Exception {
        mvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Mensalidade\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void respondeNotFoundParaPagamentoInexistente() throws Exception {
        mvc.perform(get("/pagamentos/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void documentaDoisEndpoints() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/pagamentos'].post").exists())
                .andExpect(jsonPath("$.paths['/pagamentos/{id}'].get").exists());
    }
}
