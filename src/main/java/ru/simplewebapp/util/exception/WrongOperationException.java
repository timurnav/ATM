package ru.simplewebapp.util.exception;

public class WrongOperationException extends RuntimeException {
    public WrongOperationException() {
        super("Not enough money on account balance to fulfuil your request please try again with different amount");
    }
}