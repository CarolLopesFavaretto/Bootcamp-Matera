package bootcamp.com.br.matera.domain;

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
    @Column(name = "TITULAR")
    private Titular titular;

    //enriquecendo classe com metodos

    public void debito(BigDecimal valor){
        saldo.subtract(valor);
    }

    public void credito(BigDecimal valor){
        saldo.add(valor);
    }
}
