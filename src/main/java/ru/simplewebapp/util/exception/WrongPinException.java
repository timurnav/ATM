package ru.simplewebapp.util.exception;

public class WrongPinException extends AtmException {

    public static final String WRONG_PIN = "Pin is not correct";

    public WrongPinException() {
        super(WRONG_PIN);
    }
}