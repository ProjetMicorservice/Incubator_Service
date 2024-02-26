package com.example.incubator_service.Exception;

public class UnauthorizedOperationException extends RuntimeException{
    public UnauthorizedOperationException() {
        super();
    }

    public UnauthorizedOperationException(String message) {
        super(message);
    }

    public UnauthorizedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedOperationException(Throwable cause) {
        super(cause);
    }
}
