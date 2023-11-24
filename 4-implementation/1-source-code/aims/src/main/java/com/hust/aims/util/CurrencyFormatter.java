package com.hust.aims.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    public static String format(Locale locale, Double money) {
        return NumberFormat.getCurrencyInstance(locale).format(money);
    }

    public static String vnd(Double money) {
        return NumberFormat.getCurrencyInstance(Locale.of("vi", "vn")).format(money);
    }

}
