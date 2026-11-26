package br.edu.fatec.iqs.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import br.edu.fatec.iqs.produtos.Produto;
import br.edu.fatec.iqs.produtos.ProdutoRepository;
import br.edu.fatec.iqs.produtos.ProdutoResponse;
import br.edu.fatec.iqs.produtos.ProdutoService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoSteps {
    private final ProdutoRepository repository = mock(ProdutoRepository.class);
    private final ProdutoService service = new ProdutoService(repository);
    private Produto produto;
    private List<ProdutoResponse> resultado;

    @Given("existe um produto chamado {string}")
    public void existeProduto(String nome) {
        produto = new Produto(nome, new BigDecimal("1299.90"));
    }

    @When("busco pelo nome {string}")
    public void buscoPeloNome(String nome) {
        List<Produto> encontrados = produto.getNome().toLowerCase(java.util.Locale.ROOT)
                .contains(nome.toLowerCase(java.util.Locale.ROOT)) ? List.of(produto) : List.of();
        when(repository.findByNomeContainingIgnoreCase(nome)).thenReturn(encontrados);
        resultado = service.buscarPorNome(nome);
    }

    @Then("recebo {int} produto na lista")
    public void verificoQuantidade(int quantidade) {
        assertEquals(quantidade, resultado.size());
    }
}
