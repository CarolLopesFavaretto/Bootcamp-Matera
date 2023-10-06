package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
