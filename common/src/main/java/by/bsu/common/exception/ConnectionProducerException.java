package by.bsu.common.exception;

/**
 * Created by User on 23.03.2017.
 */
public class ConnectionProducerException extends Exception {
    public ConnectionProducerException() {
    }

    public ConnectionProducerException(String message) {
        super(message);
    }

    public ConnectionProducerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionProducerException(Throwable cause) {
        super(cause);
    }
}
