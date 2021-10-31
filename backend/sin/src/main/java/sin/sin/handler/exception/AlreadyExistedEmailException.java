package sin.sin.handler.exception;

public class AlreadyExistedEmailException extends RuntimeException {
    public AlreadyExistedEmailException(String message) {
        super(message);
    }
}
