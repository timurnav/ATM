package ru.simplewebapp.Utils;

public class MoneyUtils {

    public static Integer convertFromCentsToDollars(Integer cents) {
        return cents/100;
    }

    public static Integer convertFromDollarsToCents(Integer dollars) {
        return dollars*100;
    }

}
