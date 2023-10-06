package bootcamp.com.br.matera.exception;

public class AccountNotBalanceException extends RuntimeException {
    public AccountNotBalanceException(String message) {
        super(message);
    }
}
