package bootcamp.com.br.matera.controller;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import bootcamp.com.br.matera.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public List<Conta> contas() {
        return service.List();
    }

    @GetMapping("/{id}")
    public Optional<Conta> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ContaResponse> createConta(@RequestBody ContaRequest request) {
        return service.createConta(request);
    }

    @PostMapping("/lancamentos/{id}/credito/{valor}")
    public ResponseEntity<Conta> creditar(@PathVariable Long id, @PathVariable BigDecimal valor) {
        Conta conta = service.creditar(id, valor);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping("/lancamentos/{id}/debito/{valor}")
    public ResponseEntity<Conta> debitar(@PathVariable Long id, @PathVariable BigDecimal valor) {
        Conta conta = service.debitar(id, valor);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> updateConta(@PathVariable Long id, @RequestBody Conta request) {
        return service.updateConta(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
