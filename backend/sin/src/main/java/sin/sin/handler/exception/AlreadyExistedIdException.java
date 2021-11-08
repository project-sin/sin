package sin.sin.handler.exception;

public class AlreadyExistedIdException extends RuntimeException {
    public AlreadyExistedIdException(String message) {
        super(message);
    }
}
