package org.example.Exception;

public class ApiException extends RuntimeException {
    private int statusCode;

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApiException(String message) {
        this(message, 500);
    }

    public int getStatusCode() {
        return statusCode;
    }
}