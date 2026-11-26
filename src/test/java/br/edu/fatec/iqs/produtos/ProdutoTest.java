package br.edu.fatec.iqs.produtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProdutoTest {
    @Test
    void arredondaPrecoComDuasCasas() {
        Produto produto = new Produto("Notebook", new BigDecimal("1500.995"));
        assertEquals(new BigDecimal("1501.00"), produto.getPrecoFormatado());
    }

    @Test
    void rejeitaPrecoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Notebook", null));
    }

    @Test
    void rejeitaPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Notebook", new BigDecimal("-10.00")));
    }

    @Test
    void rejeitaNomeEmBranco() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("  ", new BigDecimal("100.00")));
    }

    @Test
    void atualizaProdutoSemPerderRegrasDeValidacao() {
        Produto produto = new Produto("Teclado", new BigDecimal("150.00"));
        produto.atualizarDados(" Mouse ", new BigDecimal("89.90"));
        assertEquals("Mouse", produto.getNome());
        assertEquals(new BigDecimal("89.90"), produto.getPrecoFormatado());
    }

    @Test
    void trataPrecoAusenteSemNullPointerException() {
        Produto produto = new Produto("Mouse", new BigDecimal("80.00"));
        try {
            var campo = Produto.class.getDeclaredField("preco");
            campo.setAccessible(true);
            campo.set(produto, null);
            assertEquals(new BigDecimal("0.00"), produto.getPrecoFormatado());
        } catch (ReflectiveOperationException erro) {
            throw new AssertionError(erro);
        }
    }
}
