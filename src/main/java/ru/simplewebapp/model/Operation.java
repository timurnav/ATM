package ru.simplewebapp.model;


import java.time.LocalDateTime;

/*
* Если пользователь выбирает просмотр баланса, то в таблицу операций добавляется соответствующая запись с ID карты, временем и кодом операции.

* Если пользователь выбрал «Снятие денег», то после ввода им в окне снятия денег суммы и нажатия кнопки «ОК» проверяется, не превышает ли введённая сумма остатка на счету. В случае превышения загружается страница сообщения об ошибке, иначе – в таблицу операций добавляется запись с ID карты, кодом операции и снимаемой суммой, а в таблице карт изменяется сумма на счету, после чего загружается страница отчёта о результате операции.
 */
public class Operation {

    private long operationCode;
    private long cardNumber;
    private LocalDateTime dateTime;

    public long getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(long operationCode) {
        this.operationCode = operationCode;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
