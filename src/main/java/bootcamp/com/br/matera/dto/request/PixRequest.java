package bootcamp.com.br.matera.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixRequest {

    private String originKey;
    private String destinationKey;
    private BigDecimal balance;
}
