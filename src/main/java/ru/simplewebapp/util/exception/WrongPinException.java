package ru.simplewebapp.util.exception;

public class WrongPinException extends RuntimeException {
    public WrongPinException() {
        super("Pin is not correct");
    }
}