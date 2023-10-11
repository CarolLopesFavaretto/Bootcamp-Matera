package bootcamp.com.br.matera.service;

import bootcamp.com.br.matera.domain.Owner;
import bootcamp.com.br.matera.dto.mapper.OwnerMapper;
import bootcamp.com.br.matera.dto.request.OwnerRequest;
import bootcamp.com.br.matera.dto.response.OwnerResponse;
import bootcamp.com.br.matera.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private OwnerMapper mapper;

    @Transactional
    public ResponseEntity<OwnerResponse> createOwner(OwnerRequest request){
        Owner owner = mapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(repository.save(owner)));
    }
}
