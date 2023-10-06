package bootcamp.com.br.matera.service;

import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.dto.mapper.AccountMapper;
import bootcamp.com.br.matera.dto.request.AccountRequest;
import bootcamp.com.br.matera.dto.response.AccountResponse;
import bootcamp.com.br.matera.exception.AccountInvalidException;
import bootcamp.com.br.matera.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    @Transactional
    public ResponseEntity<AccountResponse> createConta(AccountRequest request) {
        Account account = mapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(repository.save(account)));
    }

    @Transactional
    public ResponseEntity<AccountResponse> updateConta(Long id, Account request) {
        Optional<Account> contaOptional = repository.findById(id);

        if (contaOptional.isPresent()) {
            request.setId(id);
            return ResponseEntity.ok(mapper.toResponse(repository.save(request)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Account> List() {
        return repository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    public Account debit(Long id, BigDecimal valor) {
        Optional<Account> conta = findById(id);
        if (conta.isPresent()) {
            conta.get().debit(valor);
            return repository.save(conta.get());
        }
        throw new AccountInvalidException("Conta Invalida");
    }

    public Account credit(Long id, BigDecimal valor) {
        Optional<Account> conta = findById(id);
        if (conta.isPresent()) {
            conta.get().credit(valor);
            return repository.save(conta.get());
        }
        throw new AccountInvalidException("Conta Invalida");
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<Account> contaOptional = findById(id);
        if (contaOptional.isPresent()) {
            Account account = contaOptional.get();
            repository.deleteById(account.getId());
            return ResponseEntity.noContent().build();
        }
        throw new AccountInvalidException("Conta Invalida");
    }
}
