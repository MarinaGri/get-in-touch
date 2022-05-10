package ru.itis.exceptions;

public class ConnectionLostException extends RuntimeException {

    public ConnectionLostException() {
        super();
    }

    public ConnectionLostException(String message) {
        super(message);
    }

    public ConnectionLostException(String message, Throwable cause) {
        super(message, cause);
    }
}
