package bootcamp.com.br.matera.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitularRequest {

    private String nome;
    private String cpf;
    private String email;
    private Long telefone;
}
