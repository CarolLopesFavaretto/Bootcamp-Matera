package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Owner;
import bootcamp.com.br.matera.dto.request.OwnerRequest;
import bootcamp.com.br.matera.dto.response.OwnerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    OwnerResponse save (OwnerRequest request);
}
