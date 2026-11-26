package br.edu.fatec.iqs.produtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Schema(description = "Dados para criação ou atualização de produto")
public record ProdutoRequest(
        @Schema(example = "Smartphone Xiaomi")
        @NotBlank @Size(max = 120) String nome,
        @Schema(example = "1299.90")
        @NotNull @DecimalMin("0.01") @Digits(integer = 8, fraction = 2) BigDecimal preco
) {
}
