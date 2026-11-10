package br.edu.fatec.iqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FinanceUtilsTest {
    private final FinanceUtils financeUtils = new FinanceUtils();

    @Test
    void calculaIcms() {
        assertEquals(0, new BigDecimal("170.00").compareTo(
                financeUtils.calcularImposto(new BigDecimal("1000"), "ICMS")));
    }

    @Test
    void calculaIpiAbaixoDoLimite() {
        assertEquals(0, new BigDecimal("250.00").compareTo(
                financeUtils.calcularImposto(new BigDecimal("5000"), "IPI")));
    }

    @Test
    void calculaIpiAcimaDoLimite() {
        assertEquals(0, new BigDecimal("1500.00").compareTo(
                financeUtils.calcularImposto(new BigDecimal("15000"), "IPI")));
    }

    @Test
    void retornaZeroParaTipoDesconhecido() {
        assertEquals(BigDecimal.ZERO, financeUtils.calcularImposto(BigDecimal.TEN, "ISS"));
    }
}
