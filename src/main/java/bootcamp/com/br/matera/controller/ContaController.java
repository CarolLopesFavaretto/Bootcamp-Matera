package bootcamp.com.br.matera.controller;

import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import bootcamp.com.br.matera.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    public ResponseEntity<ContaResponse> createconta(@RequestBody ContaRequest request){
        return service.createConta(request);
    }
}
