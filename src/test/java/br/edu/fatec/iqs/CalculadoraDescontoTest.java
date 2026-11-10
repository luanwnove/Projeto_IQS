package br.edu.fatec.iqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CalculadoraDescontoTest {
    private final CalculadoraDesconto calculadora = new CalculadoraDesconto();

    @Test
    void aplicaDescontoPadrao() {
        assertEquals(0, new BigDecimal("95.00").compareTo(
                calculadora.calcularTotal(new BigDecimal("100"))));
    }

    @Test
    void aplicaDescontoAlto() {
        assertEquals(0, new BigDecimal("450.00").compareTo(
                calculadora.calcularTotal(new BigDecimal("500"))));
    }

    @Test
    void rejeitaSubtotalNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcularTotal(new BigDecimal("-1")));
    }
}
