package br.edu.fatec.iqs.financeiro;

/**
 * Disponibiliza operações financeiras de cálculo de juros.
 *
 * <p>Exemplo de uso: {@code new Financeira().jurosCompostos(1000, 0.02, 12)}.
 *
 * @author Luan Roberto da Silva
 * @version 1.0
 * @see Math#pow(double, double)
 */
public final class Financeira {
    /**
     * Inicializa uma calculadora de juros compostos.
     */
    public Financeira() {
    }

    /**
     * Calcula o montante de uma aplicação com juros compostos.
     *
     * @param principal capital inicial não negativo
     * @param taxa taxa por período, em representação decimal
     * @param meses quantidade de períodos, não negativa
     * @return montante acumulado ao final do período
     * @throws IllegalArgumentException quando os argumentos são inválidos
     * @see Math#pow(double, double)
     */
    public double jurosCompostos(double principal, double taxa, int meses) {
        if (!Double.isFinite(principal) || principal < 0
                || !Double.isFinite(taxa) || taxa < 0 || meses < 0) {
            throw new IllegalArgumentException("Valores financeiros inválidos");
        }
        double resultado = principal * Math.pow(1 + taxa, meses);
        if (!Double.isFinite(resultado)) {
            throw new IllegalArgumentException("Resultado fora do intervalo permitido");
        }
        return resultado;
    }
}
