package bootcamp.com.br.matera.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PixResponse {

    private UUID id;
    private BigDecimal originBalance;
    private BigDecimal destinationBalance;


    public PixResponse(BigDecimal originBalance, BigDecimal destinationBalance) {
        this.id = UUID.randomUUID();
        this.originBalance = originBalance;
        this.destinationBalance = destinationBalance;
    }
}
