package bootcamp.com.br.matera.Data;

import bootcamp.com.br.matera.domain.Account;

import java.math.BigDecimal;

public class AccountData {


    public static Account getAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setNumberAccount("12345-6");
        account.setAgency("12384-xx");
        account.setBalance(BigDecimal.valueOf(100));
        return account;
    }

}
