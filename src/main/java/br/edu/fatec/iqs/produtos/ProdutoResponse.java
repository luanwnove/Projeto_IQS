package br.edu.fatec.iqs.produtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Produto cadastrado")
public record ProdutoResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "Smartphone Xiaomi") String nome,
        @Schema(example = "1299.90") BigDecimal preco
) {
    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getPrecoFormatado());
    }
}
