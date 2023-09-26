package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Titular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitularRepository extends JpaRepository<Titular, Long> {
}
