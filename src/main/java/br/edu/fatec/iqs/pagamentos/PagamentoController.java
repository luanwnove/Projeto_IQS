package br.edu.fatec.iqs.pagamentos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos", description = "Registro e consulta de pagamentos")
public class PagamentoController {
    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar pagamento", description = "Registra um pagamento e devolve seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pagamento registrado", content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PagamentoResponse> criar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do pagamento", required = true)
            @Valid @RequestBody PagamentoRequest requisicao) {
        PagamentoResponse pagamento = service.criar(requisicao);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pagamento.id())
                .toUri();
        return ResponseEntity.created(localizacao).body(pagamento);
    }

    @Operation(summary = "Consultar pagamento por ID", description = "Retorna um pagamento registrado anteriormente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado", content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscar(
            @Parameter(description = "Identificador do pagamento", example = "1")
            @PathVariable long id) {
        PagamentoResponse pagamento = service.buscarPorId(id);
        return pagamento == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pagamento);
    }
}
