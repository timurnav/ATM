package ru.simplewebapp.model;

import org.hibernate.validator.constraints.NotEmpty;
import ru.simplewebapp.util.exception.LockedAccountException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNTS")
public class Account extends BaseEntity{

    public static final Integer MAX_ATTEMPTS = 4;

    @Column(name = "number", nullable = false, unique = true)
    @NotEmpty
    private String number;

    @Column(name = "pin", nullable = false)
    @NotEmpty
    private String pin;

    @Column(name = "balance", columnDefinition = "default 0")
    private Integer balance;

    @Column(name = "attempt", columnDefinition = "default 0")
    private Integer attempt;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Account() {
    }

    protected Account(Integer id) {
        this.id = id;
    }

    public Account(String cardNumber, String pin) {
        this(cardNumber, pin, 0, 0);
    }

    public Account(String number, String pin, int balance, int attempt) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
        this.attempt = attempt;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getBalance() {
        return balance;
    }

    public void withdraw(Integer amount) {
        balance -= amount;
    }

    public void deposit(Integer amount) {
        balance += amount;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void resetFailAttempts() {
        attempt = 0;
    }

    public int incrementFailAttempt() {
        return MAX_ATTEMPTS - attempt++;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", dateTime=" + dateTime +
                '}';
    }

    public Account ifHasAttempts() throws LockedAccountException {
        if (getAttempt() < MAX_ATTEMPTS) {
            return this;
        }
        throw new LockedAccountException();
    }
}
