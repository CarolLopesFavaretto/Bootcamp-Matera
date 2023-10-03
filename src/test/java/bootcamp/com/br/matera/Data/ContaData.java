package bootcamp.com.br.matera.Data;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.request.ContaRequest;

import java.math.BigDecimal;

public class ContaData {


    public static Conta getConta(){
       Conta conta = new Conta();
       conta.setId(1L);
       conta.setNumeroConta("12345-6");
       conta.setAgencia("12384-xx");
       conta.setSaldo(BigDecimal.valueOf(100.0));
        return conta;
    }

    public static ContaRequest request(){
        ContaRequest request = new ContaRequest();
        request.setNumeroConta("12345-6");
        request.setAgencia("12384-xx");
        request.setSaldo(BigDecimal.valueOf(100.0));
        return request;
    }
}
