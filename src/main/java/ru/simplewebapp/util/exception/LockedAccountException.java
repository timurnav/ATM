package ru.simplewebapp.util.exception;

public class LockedAccountException  extends RuntimeException {
    public LockedAccountException(String message) {
        super(message);
    }
}
