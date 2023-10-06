package bootcamp.com.br.matera.domain;

import bootcamp.com.br.matera.exception.AccountInvalidException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NUMERO")
    private String numberAccount;
    @Column(name = "AGENCIA")
    private String agency;
    @Column(name = "SALDO")
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne
    private Owner owner;

    //enriquecendo classe com metodos

    public void debit(BigDecimal value) {
        if (this.balance.compareTo(value) < 0) {
            throw new AccountInvalidException("Conta sem saldo disponível.");
        }
        balance = balance.subtract(value).setScale(2);
    }

    public void credit(BigDecimal value) {
        balance = balance.add(value).setScale(2);
    }

    public void sendPix(Account destiny, BigDecimal value) {
        this.debit(value);
        destiny.credit(value);
    }
}

