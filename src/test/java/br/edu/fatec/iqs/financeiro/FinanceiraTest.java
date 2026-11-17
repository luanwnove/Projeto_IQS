package br.edu.fatec.iqs.financeiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class FinanceiraTest {
    private final Financeira financeira = new Financeira();

    @Test
    void calculaJurosCompostos() {
        assertEquals(1126.1624192640002, financeira.jurosCompostos(1000, 0.02, 6), 0.0000001);
    }

    @Test
    void rejeitaPeriodoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> financeira.jurosCompostos(1000, 0.02, -1));
    }

    @Test
    void rejeitaTaxaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> financeira.jurosCompostos(1000, Double.NaN, 1));
    }
}
