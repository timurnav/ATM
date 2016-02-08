package ru.simplewebapp.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,
        reason = NotEnoughMoneyException.ILLEGAL_OPERATION)
public class NotEnoughMoneyException extends AtmException {

    public static final String ILLEGAL_OPERATION =
            "Not enough money on account balance to execute your " +
                    "request please try again with different amount";

    public NotEnoughMoneyException() {
        super(ILLEGAL_OPERATION);
    }
}