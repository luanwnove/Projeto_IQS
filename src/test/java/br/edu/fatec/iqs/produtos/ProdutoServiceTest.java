package br.edu.fatec.iqs.produtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void deveBuscarProdutosPorNome() {
        when(repository.findByNomeContainingIgnoreCase("Xiaomi"))
                .thenReturn(List.of(new Produto("Smartphone Xiaomi", new BigDecimal("1299.90"))));

        List<ProdutoResponse> resultado = service.buscarPorNome("Xiaomi");

        assertEquals(1, resultado.size());
        assertEquals("Smartphone Xiaomi", resultado.get(0).nome());
        verify(repository).findByNomeContainingIgnoreCase("Xiaomi");
    }

    @Test
    void deveRejeitarBuscaVazia() {
        ResponseStatusException erro = assertThrows(ResponseStatusException.class,
                () -> service.buscarPorNome(" "));
        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());
    }

    @Test
    void deveResponderNaoEncontrado() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        ResponseStatusException erro = assertThrows(ResponseStatusException.class,
                () -> service.buscarPorId(999L));
        assertEquals(HttpStatus.NOT_FOUND, erro.getStatusCode());
    }
}
