package ru.simplewebapp.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

// TODO needs to be in english

/*
* Если пользователь выбирает просмотр баланса, то в таблицу операций добавляется соответствующая запись с ID карты, временем и кодом операции.

* Если пользователь выбрал «Снятие денег», то после ввода им в окне снятия денег суммы и нажатия кнопки «ОК» проверяется, не превышает ли введённая сумма остатка на счету. В случае превышения загружается страница сообщения об ошибке, иначе – в таблицу операций добавляется запись с ID карты, кодом операции и снимаемой суммой, а в таблице карт изменяется сумма на счету, после чего загружается страница отчёта о результате операции.
 */

@Entity
@Table(name = "OPERATIONS")
public class Operation {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "ops_types", joinColumns = @JoinColumn(name = "operation_id"))
    @Column(name = "type")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Type> operationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Operation() {
    }

    public Operation(Set<Type> operationCode, Account account, LocalDateTime dateTime, Integer amount) {
        this.operationCode = operationCode;
        this.account = account;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operationCode=" + operationCode +
                ", account=" + account +
                ", dateTime=" + dateTime +
                ", amount=" + amount +
                '}';
    }
}
