package bootcamp.com.br.matera.service;

import bootcamp.com.br.matera.domain.Owner;
import bootcamp.com.br.matera.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repository;

    @Transactional
    public Owner createOwner(Owner request) {
        return repository.save(request);
    }
}