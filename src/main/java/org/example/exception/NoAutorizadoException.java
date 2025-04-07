package org.example.exception;

public class NoAutorizadoException extends RuntimeException {
    public NoAutorizadoException(String message)
    {
        super(message);
    }
}
