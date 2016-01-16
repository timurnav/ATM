package ru.simplewebapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number", nullable = false)
    @NotNull
    private String number;

    @Column(name = "pin", nullable = false)
    @NotNull
    private String pin;

    @Column(name = "amount", nullable = false)
    @NotNull
    private Integer amount;

    @Transient
    private Integer attempt = 0;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    protected Account(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Account(String number, String pin, int amount, int attempt) {
        this.number = number;
        this.pin = pin;
        this.amount = amount;
        this.attempt = attempt;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Account(String cardNumber, String pin) {
        this(cardNumber, pin, 0, 0);
    }

    public Account() {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
}
