package bootcamp.com.br.matera.controller;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import bootcamp.com.br.matera.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public List<Conta> contas(){
        return service.contaList();
    }

    @GetMapping("/{id}")
    public Optional<Conta> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ContaResponse> createconta(@RequestBody ContaRequest request){
        return service.createConta(request);
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
