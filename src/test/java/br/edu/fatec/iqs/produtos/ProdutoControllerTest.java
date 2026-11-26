package br.edu.fatec.iqs.produtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {
    @Mock
    private ProdutoService service;

    @InjectMocks
    private ProdutoController controller;

    @Test
    void deveChamarServiceAoListar() {
        when(service.listar()).thenReturn(List.of());
        assertEquals(List.of(), controller.listar());
        verify(service).listar();
    }

    @Test
    void deveChamarServiceNaBuscaPorNome() {
        when(service.buscarPorNome("Xiaomi")).thenReturn(List.of());
        assertEquals(List.of(), controller.buscarPorNome("Xiaomi"));
        verify(service).buscarPorNome("Xiaomi");
    }
}
