package br.edu.fatec.iqs.produtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    public Produto(String nome, BigDecimal preco) {
        atualizarDados(nome, preco);
    }

    public void atualizarDados(String nome, BigDecimal preco) {
        if (nome == null || nome.isBlank() || nome.strip().length() > 120) {
            throw new IllegalArgumentException("Nome do produto inválido");
        }
        if (preco == null || preco.signum() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        this.nome = nome.strip();
        this.preco = preco.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPrecoFormatado() {
        return preco == null ? BigDecimal.ZERO.setScale(2) : preco.setScale(2, RoundingMode.HALF_UP);
    }
}
