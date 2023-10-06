package bootcamp.com.br.matera.controller;

import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.dto.request.AccountRequest;
import bootcamp.com.br.matera.dto.response.AccountResponse;
import bootcamp.com.br.matera.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public List<Account> accounts() {
        return service.List();
    }

    @GetMapping("/{id}")
    public Optional<Account> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return service.createConta(request);
    }

    @PostMapping("/entry/{id}/credit/{value}")
    public ResponseEntity<Account> credit(@PathVariable Long id, @PathVariable BigDecimal value) {
        Account account = service.credit(id, value);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PostMapping("/entry/{id}/debit/{value}")
    public ResponseEntity<Account> debit(@PathVariable Long id, @PathVariable BigDecimal value) {
        Account account = service.debit(id, value);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @RequestBody Account request) {
        return service.updateConta(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}