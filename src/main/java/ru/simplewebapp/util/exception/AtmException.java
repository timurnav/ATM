package ru.simplewebapp.util.exception;

public abstract class AtmException extends RuntimeException {

    public AtmException(String message) {
        super(message);
    }
}
