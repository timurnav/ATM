package ru.simplewebapp.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "number", nullable = false)
    @NotEmpty
    private String number;

    @Column(name = "pin", nullable = false)
    @NotEmpty
    private String pin;

    @Column(name = "balance", columnDefinition = "default 0")
    private Integer balance;

    @Transient
    private Integer attempt = 0;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void setNumber(String number) {
        this.number = number;
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

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public void dropAttempts() {
        attempt = 0;
    }

    public void incrementAttempt() {
        attempt++;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", dateTime=" + dateTime +
                '}';
    }
}
