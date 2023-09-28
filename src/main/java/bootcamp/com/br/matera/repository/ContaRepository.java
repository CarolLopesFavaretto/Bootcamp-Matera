package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

        Optional<ContaResponse> save (ContaRequest request);

}
