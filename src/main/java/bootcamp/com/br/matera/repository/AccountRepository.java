package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByOwnerCpf(String cpf);

}
