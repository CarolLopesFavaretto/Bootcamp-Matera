package bootcamp.com.br.matera.exception.handler;

import bootcamp.com.br.matera.exception.ContaInvalidaException;
import bootcamp.com.br.matera.exception.ContaSemSaldoException;
import bootcamp.com.br.matera.exception.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(ContaInvalidaException.class)
    public ResponseEntity<MessageError> contaInvalidaException(ContaInvalidaException ex) {
        MessageError mensagemErro = new MessageError(ex.getMessage());
        return new ResponseEntity<>(mensagemErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContaSemSaldoException.class)
    public ResponseEntity<MessageError> contaSemSaldoException(ContaSemSaldoException ex) {
        MessageError mensagemErro = new MessageError(ex.getMessage());
        return new ResponseEntity<>(mensagemErro, HttpStatus.CONFLICT);
    }
}
