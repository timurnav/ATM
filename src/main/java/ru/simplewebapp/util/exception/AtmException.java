package ru.simplewebapp.util.exception;

public class AtmException extends RuntimeException {

    public static final String WRONG_PIN = "Pin is not correct";
    public static final String ILLEGAL_OPERATION =
            "Not enough money on account balance to fulfuil your request please try again with different amount";
    public static final String ACCOUNT_WAS_LOCKED = "Account was locked";


    public AtmException(String message) {
        super(message);
    }
}
