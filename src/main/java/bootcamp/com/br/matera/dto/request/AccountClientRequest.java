package bootcamp.com.br.matera.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountClientRequest {

    private String numberAccount;
    private String agency;
    private String cpf;
    private BigDecimal balance = BigDecimal.ZERO;
    private Boolean active = Boolean.TRUE;

}
