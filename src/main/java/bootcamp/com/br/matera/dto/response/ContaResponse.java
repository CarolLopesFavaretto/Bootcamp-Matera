package bootcamp.com.br.matera.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaResponse {

    private String numeroConta;
    private String agencia;
    private BigDecimal saldo;
}
