package bootcamp.com.br.matera.service;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.mapper.ContaMapper;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import bootcamp.com.br.matera.exception.ContaInvalidaException;
import bootcamp.com.br.matera.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaMapper mapper;

    public ResponseEntity<ContaResponse> createConta(ContaRequest request) {
        Conta conta = mapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(repository.save(conta)));
    }

    public List<Conta> contaList() {
        return repository.findAll();
    }

    public Optional<Conta> findById(Long id) {
        return repository.findById(id);
    }

    public Conta debitar(Long id, BigDecimal valor) {
        Optional<Conta> conta = findById(id);
        if (conta.isPresent()) {
            conta.get().debito(valor);
            return repository.save(conta.get());
        }
        throw new ContaInvalidaException("Conta Invalida");
    }

    public Conta creditar(Long id, BigDecimal valor) {
        Optional<Conta> conta = findById(id);
        if (conta.isPresent()) {
            conta.get().credito(valor);
            return repository.save(conta.get());
        }
        throw new ContaInvalidaException("Conta Invalida");
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
