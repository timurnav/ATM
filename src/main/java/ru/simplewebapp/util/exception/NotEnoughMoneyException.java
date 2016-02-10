package ru.simplewebapp.util.exception;

public class NotEnoughMoneyException extends AtmException {

    public static final String ILLEGAL_OPERATION =
            "Not enough money on account balance to execute your " +
                    "request please try again with different amount";

    public NotEnoughMoneyException() {
        super(ILLEGAL_OPERATION);
    }
}