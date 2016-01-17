package ru.simplewebapp.util.exception;

public class WrongPinException extends RuntimeException {
    public WrongPinException(String message) {
        super(message);
    }
}