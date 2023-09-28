package bootcamp.com.br.matera.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageError {

    private String mensagem;
    private LocalDateTime timestamp;

    public MessageError(String mensagem) {
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }
}
