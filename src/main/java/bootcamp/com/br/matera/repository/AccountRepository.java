package bootcamp.com.br.matera.repository;

import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.dto.request.AccountRequest;
import bootcamp.com.br.matera.dto.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    AccountResponse save(AccountRequest request);

    Account findByOwnerCpf (String cpf);

}
