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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaMapper mapper;

    @Transactional
    public ResponseEntity<ContaResponse> createConta(ContaRequest request) {
        Conta conta = mapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(repository.save(conta)));
    }

    @Transactional
    public ResponseEntity<ContaResponse> updateConta(Long id, Conta request) {
        Optional<Conta> contaOptional = repository.findById(id);

        if (contaOptional.isPresent()) {
            request.setId(id);
            return ResponseEntity.ok(mapper.toResponse(repository.save(request)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Conta> List() {
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

    public ResponseEntity<Void> delete(Long id) {
        Optional<Conta> contaOptional = findById(id);
        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            repository.deleteById(conta.getId());
            return ResponseEntity.noContent().build();
        }
        throw new ContaInvalidaException("Conta Invalida");
    }
}
