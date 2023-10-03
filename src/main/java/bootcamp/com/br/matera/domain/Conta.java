package bootcamp.com.br.matera.domain;

import bootcamp.com.br.matera.exception.ContaInvalidaException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NUMERO")
    private String numeroConta;
    @Column(name = "AGENCIA")
    private String agencia;
    @Column(name = "SALDO")
    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToOne
    private Titular titular;

    //enriquecendo classe com metodos

    public void debito(BigDecimal valor) {
        if (this.saldo.compareTo(valor) < 0) {
            throw new ContaInvalidaException("Conta sem saldo disponível.");
        }
        saldo = saldo.subtract(valor);
    }

    public void credito(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public void enviarPix(Conta contaDestino, BigDecimal valor) {
//        if (this.saldo.compareTo(valor) <= 0) {
//            throw new ContaInvalidaException("Conta sem saldo disponível.");
//        }
        this.debito(valor);
        contaDestino.credito(valor);
    }
}

