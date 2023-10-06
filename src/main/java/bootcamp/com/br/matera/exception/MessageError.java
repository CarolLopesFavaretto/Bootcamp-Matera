package bootcamp.com.br.matera.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageError {

    private String message;
    private LocalDateTime timestamp;

    public MessageError(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
