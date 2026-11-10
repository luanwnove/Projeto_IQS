package br.edu.fatec.iqs;

import java.math.BigDecimal;
import java.util.Map;

public final class FinanceUtils {
    private static final BigDecimal LIMITE_IPI = new BigDecimal("10000");
    private static final Map<String, ImpostoStrategy> ESTRATEGIAS = Map.of(
            "ICMS", valor -> valor.multiply(new BigDecimal("0.17")),
            "IPI", valor -> valor.multiply(valor.compareTo(LIMITE_IPI) > 0
                    ? new BigDecimal("0.10") : new BigDecimal("0.05"))
    );

    public BigDecimal calcularImposto(BigDecimal valor, String tipo) {
        if (valor == null || tipo == null) {
            return BigDecimal.ZERO;
        }
        ImpostoStrategy estrategia = ESTRATEGIAS.get(tipo);
        return estrategia == null ? BigDecimal.ZERO : estrategia.calcular(valor);
    }
}
