package com.example.incubator_service.Exception;

public class NoAvailableIncubatorException extends RuntimeException {

    public NoAvailableIncubatorException() {
        super();
    }

    public NoAvailableIncubatorException(String message) {
        super(message);
    }

    public NoAvailableIncubatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAvailableIncubatorException(Throwable cause) {
        super(cause);
    }
}
