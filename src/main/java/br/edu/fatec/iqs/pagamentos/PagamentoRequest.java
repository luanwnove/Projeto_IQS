package br.edu.fatec.iqs.pagamentos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Informações para registrar um pagamento")
public record PagamentoRequest(
        @Schema(description = "Descrição do pagamento", example = "Mensalidade")
        @NotBlank String descricao,
        @Schema(description = "Valor do pagamento em reais", example = "150.00")
        @NotNull @DecimalMin("0.01") BigDecimal valor
) {
}
