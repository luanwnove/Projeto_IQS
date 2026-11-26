package br.edu.fatec.iqs.produtos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ProdutoValidacaoParametrizadaTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t"})
    void deveRejeitarNomesInvalidos(String nome) {
        assertThrows(IllegalArgumentException.class, () -> new Produto(nome, BigDecimal.ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-99.99"})
    void deveRejeitarPrecosNaoPositivos(String preco) {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Produto", new BigDecimal(preco)));
    }
}
