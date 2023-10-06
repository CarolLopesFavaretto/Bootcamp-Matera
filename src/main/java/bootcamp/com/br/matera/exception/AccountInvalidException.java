package bootcamp.com.br.matera.exception;

public class AccountInvalidException extends RuntimeException {
    public AccountInvalidException(String message) {
        super(message);
    }
}
