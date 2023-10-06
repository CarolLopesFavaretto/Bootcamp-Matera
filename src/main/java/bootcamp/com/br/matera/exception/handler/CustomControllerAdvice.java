package bootcamp.com.br.matera.exception.handler;

import bootcamp.com.br.matera.exception.AccountInvalidException;
import bootcamp.com.br.matera.exception.AccountNotBalanceException;
import bootcamp.com.br.matera.exception.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(AccountInvalidException.class)
    public ResponseEntity<MessageError> accountInvalidException(AccountInvalidException ex) {
        MessageError messageError = new MessageError(ex.getMessage());
        return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotBalanceException.class)
    public ResponseEntity<MessageError> accountNotBalanceException(AccountNotBalanceException ex) {
        MessageError messageError = new MessageError(ex.getMessage());
        return new ResponseEntity<>(messageError, HttpStatus.CONFLICT);
    }
}
