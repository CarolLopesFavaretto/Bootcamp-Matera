package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
