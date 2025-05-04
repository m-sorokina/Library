package exceptions;

public class WrongValueException extends RuntimeException {

    public WrongValueException() {
        super();
    }

    public WrongValueException(String message) {
        super(message + "\nRecord was not saved.");
    }

}
