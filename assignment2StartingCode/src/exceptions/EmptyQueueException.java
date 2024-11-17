package exceptions;

/**
 * This exception is thrown when an operation is performed on an empty queue.
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String message) {
        super(message);
    }
}
