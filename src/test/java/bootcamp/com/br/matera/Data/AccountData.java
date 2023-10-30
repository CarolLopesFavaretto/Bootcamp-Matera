package bootcamp.com.br.matera.Data;

import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.domain.Owner;

import java.math.BigDecimal;

public class AccountData {


    public static Account getCreateAccount(Long idAccount) {
        Account account = new Account();
        account.setId(idAccount);
        account.setNumberAccount("12345-6");
        account.setAgency("12384-xx");
        account.setBalance(BigDecimal.valueOf(100));
        account.setOwner(getOwner(1L));
        return account;
    }

    public static Account getCreateAccount1(Long idAccount) {
        Account account = new Account();
        account.setId(idAccount);
        account.setNumberAccount("12345-6");
        account.setAgency("12384-xx");
        account.setBalance(BigDecimal.valueOf(100));
        account.setOwner(getOwner1(2L));
        return account;
    }

    public static Account getAccount(Long idAccount) {
        Account account = new Account();
        account.setId(idAccount);
        account.setNumberAccount("12345-6");
        account.setAgency("12384-xx");
        account.setBalance(BigDecimal.valueOf(100));
        return account;
    }

    public static Owner getOwner(Long ownerId) {
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setName("Caroline");
        owner.setCpf("071.669.720-33");
        owner.setEmail("carol@gmail.com");
        owner.setTelephone(11947759525L);
        return owner;
    }

    public static Owner getOwner1(Long ownerId) {
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setName("Caroline");
        owner.setCpf("41246532808");
        owner.setEmail("carol@gmail.com");
        owner.setTelephone(11947759525L);
        return owner;
    }
}
