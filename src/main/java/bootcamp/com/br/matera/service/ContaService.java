package bootcamp.com.br.matera.service;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.mapper.ContaMapper;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import bootcamp.com.br.matera.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
