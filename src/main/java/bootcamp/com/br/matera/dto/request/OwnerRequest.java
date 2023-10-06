package bootcamp.com.br.matera.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {

    private String name;
    private String cpf;
    private String email;
    private Long telephone;
}
