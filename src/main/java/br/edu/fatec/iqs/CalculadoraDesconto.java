package br.edu.fatec.iqs;

import java.math.BigDecimal;

public final class CalculadoraDesconto {
    private static final BigDecimal LIMITE = new BigDecimal("500");
    private static final BigDecimal DESCONTO_PADRAO = new BigDecimal("0.05");
    private static final BigDecimal DESCONTO_ALTO = new BigDecimal("0.10");

    public BigDecimal calcularTotal(BigDecimal subtotal) {
        if (subtotal == null || subtotal.signum() < 0) {
            throw new IllegalArgumentException("Subtotal deve ser positivo ou zero");
        }
        BigDecimal taxa = subtotal.compareTo(LIMITE) >= 0 ? DESCONTO_ALTO : DESCONTO_PADRAO;
        return subtotal.subtract(subtotal.multiply(taxa));
    }
}
