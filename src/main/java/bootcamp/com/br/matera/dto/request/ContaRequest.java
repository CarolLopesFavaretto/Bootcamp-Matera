package bootcamp.com.br.matera.dto.request;

import bootcamp.com.br.matera.domain.Titular;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaRequest {

    private String numeroConta;
    private String agencia;
    private BigDecimal saldo;
    private TitularRequest titular;
}
