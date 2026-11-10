package br.edu.fatec.iqs;

import java.math.BigDecimal;

@FunctionalInterface
public interface ImpostoStrategy {
    BigDecimal calcular(BigDecimal valor);
}
