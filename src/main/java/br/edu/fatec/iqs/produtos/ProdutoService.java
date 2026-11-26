package br.edu.fatec.iqs.produtos;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listar() {
        return repository.findAll().stream().map(ProdutoResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Long id) {
        return ProdutoResponse.from(obterProduto(id));
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe um nome para a busca");
        }
        return repository.findByNomeContainingIgnoreCase(nome.strip()).stream()
                .map(ProdutoResponse::from)
                .toList();
    }

    @Transactional
    public ProdutoResponse criar(ProdutoRequest dados) {
        Produto produto = repository.save(new Produto(dados.nome(), dados.preco()));
        return ProdutoResponse.from(produto);
    }

    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest dados) {
        Produto produto = obterProduto(id);
        produto.atualizarDados(dados.nome(), dados.preco());
        return ProdutoResponse.from(repository.save(produto));
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = obterProduto(id);
        repository.delete(produto);
    }

    private Produto obterProduto(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}
