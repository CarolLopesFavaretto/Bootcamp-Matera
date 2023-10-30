package bootcamp.com.br.matera.service;

import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.domain.Owner;
import bootcamp.com.br.matera.dto.mapper.AccountMapper;
import bootcamp.com.br.matera.dto.request.PixRequest;
import bootcamp.com.br.matera.dto.response.AccountResponse;
import bootcamp.com.br.matera.dto.response.PixResponse;
import bootcamp.com.br.matera.exception.AccountInvalidException;
import bootcamp.com.br.matera.repository.AccountRepository;
import bootcamp.com.br.matera.repository.OwnerRepository;
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
    private OwnerRepository ownerRepository;

    @Autowired
    private AccountMapper mapper;

    @Transactional
    public ResponseEntity<Account> createAccount(Long ownerId, Account request) {
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        if(owner.isPresent()){
            request.setOwner(owner.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(request));
        }
        return ResponseEntity.notFound().build();

    }

    @Transactional
    public ResponseEntity<AccountResponse> updateAccount(Long id, Account request) {
        Optional<Account> accountOptional = repository.findById(id);

        if (accountOptional.isPresent()) {
            request.setId(id);
            return ResponseEntity.ok(mapper.toResponse(repository.save(request)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public PixResponse pix(PixRequest request) {
        Account accountOrigin = repository.findByOwnerCpf(request.getOriginKey());
        Account accountDestination = repository.findByOwnerCpf(request.getDestinationKey());
        accountOrigin.sendPix(accountDestination, request.getBalance());
        repository.saveAll(List.of(accountOrigin, accountDestination));
        return new PixResponse(accountOrigin.getBalance(), accountDestination.getBalance());

    }

    public List<Account> List() {
        return repository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    public Account debit(Long id, BigDecimal value) {
        Optional<Account> account = findById(id);
        if (account.isPresent()) {
            account.get().debit(value);
            return repository.save(account.get());
        }
        throw new AccountInvalidException("Conta Invalida");
    }

    public Account credit(Long id, BigDecimal value) {
        Optional<Account> account = findById(id);
        if (account.isPresent()) {
            account.get().credit(value);
            return repository.save(account.get());
        }
        throw new AccountInvalidException("Conta Invalida");
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<Account> accountOptional = findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            repository.deleteById(account.getId());
            return ResponseEntity.noContent().build();
        }
        throw new AccountInvalidException("Conta Invalida");
    }
}
