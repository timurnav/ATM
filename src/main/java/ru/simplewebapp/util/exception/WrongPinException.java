package ru.simplewebapp.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = WrongPinException.WRONG_PIN)
public class WrongPinException extends AtmException {

    public static final String WRONG_PIN = "Pin is not correct";

    public WrongPinException() {
        super(WRONG_PIN);
    }
}