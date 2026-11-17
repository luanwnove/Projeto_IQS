package br.edu.fatec.iqs.pagamentos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    private final ConcurrentMap<Long, PagamentoResponse> pagamentos = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    public PagamentoResponse criar(PagamentoRequest requisicao) {
        long id = sequencia.incrementAndGet();
        PagamentoResponse pagamento = new PagamentoResponse(id, requisicao.descricao().trim(), requisicao.valor());
        pagamentos.put(id, pagamento);
        return pagamento;
    }

    public PagamentoResponse buscarPorId(long id) {
        return pagamentos.get(id);
    }
}
