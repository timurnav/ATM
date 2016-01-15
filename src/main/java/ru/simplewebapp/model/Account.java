package ru.simplewebapp.model;

public class Account {
    private long cardNumber;
    private int pinCode;
    private int amount;
    private int attempt;

    public Account(long cardNumber, int pinCode, int amount, int attempt) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.amount = amount;
        this.attempt = attempt;
    }

    public Account(long cardNumber, int pinCode) {
        this(cardNumber, pinCode, 0, 0);
    }

    public Account() {
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public void dropAttempts() {
        attempt = 0;
    }

    public void incrementAttempt() {
        attempt++;
    }
}
