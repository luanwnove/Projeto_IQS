package br.edu.fatec.iqs.pagamentos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Pagamento registrado")
public record PagamentoResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "Mensalidade") String descricao,
        @Schema(example = "150.00") BigDecimal valor
) {
}
