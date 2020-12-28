package exceptions;

public class NotCorrectPizzaDataException extends Exception {
    public NotCorrectPizzaDataException() {
    }

    public NotCorrectPizzaDataException(String message) {
        super(message);
    }

    public NotCorrectPizzaDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCorrectPizzaDataException(Throwable cause) {
        super(cause);
    }

    public NotCorrectPizzaDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
