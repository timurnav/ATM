package ru.simplewebapp.util.exception;

public class NotFoundException extends AtmException {

    public static final String NO_DATA_FOUND = "No data found";

    public NotFoundException() {
        super(NO_DATA_FOUND);
    }
}
