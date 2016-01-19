package ru.simplewebapp.util.exception;

public class LockedAccountException  extends RuntimeException {
    public LockedAccountException() {
        super("Account was locked");
    }
}
