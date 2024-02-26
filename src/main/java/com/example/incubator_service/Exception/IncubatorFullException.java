package com.example.incubator_service.Exception;

public class IncubatorFullException extends RuntimeException {

    public IncubatorFullException() {
        super();
    }

    public IncubatorFullException(String message) {
        super(message);
    }

    public IncubatorFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncubatorFullException(Throwable cause) {
        super(cause);
    }
}
