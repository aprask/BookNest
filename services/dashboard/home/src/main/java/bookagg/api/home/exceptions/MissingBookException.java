package bookagg.api.home.exceptions;

public class MissingBookException extends RuntimeException {

    public MissingBookException(String message) {
        super(message);
    }

    public MissingBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
