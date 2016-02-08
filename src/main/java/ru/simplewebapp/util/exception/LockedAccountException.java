package ru.simplewebapp.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED,
        reason = LockedAccountException.ACCOUNT_WAS_LOCKED)
public class LockedAccountException extends AtmException {

    public static final String ACCOUNT_WAS_LOCKED = "Account was locked";

    public LockedAccountException() {
        super(ACCOUNT_WAS_LOCKED);
    }
}
