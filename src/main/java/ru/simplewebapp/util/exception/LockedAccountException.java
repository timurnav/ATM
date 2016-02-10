package ru.simplewebapp.util.exception;

public class LockedAccountException extends AtmException {

    public static final String ACCOUNT_WAS_LOCKED = "Account was locked";

    public LockedAccountException() {
        super(ACCOUNT_WAS_LOCKED);
    }
}
