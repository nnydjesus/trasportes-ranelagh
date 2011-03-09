package ar.com.nny.base.exception;

/**
 * This exception is thrown to indicate an error in the application.
 * Examples of errors can be a file open error, database error, or the such.
 * <p/>
 * This kind of exception are usually irrecoverable, and end up in an error
 * message to the user indicating an error in the system.
 *
 */
@SuppressWarnings("serial")
public class NonBusinessException extends RuntimeException {

    public NonBusinessException(String message) {
        super(message);
    }


    public NonBusinessException(Throwable cause) {
        super(cause);
    }


    public NonBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
