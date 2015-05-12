package com.zanox;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by jsoler on 11.05.15.
 */
public class PayRoll {

    private Month month;
    private Employee employee;

    public PayRoll(Month month, Employee employee) {
        this.month = month;
        this.employee = employee;
    }

    public Integer calculate() {
        Integer taxes = 1;
        return employee.getSalary() - taxes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("For the Month of : " + month.getDisplayName(TextStyle.SHORT, Locale.GERMANY));
        sb.append("Employee: " + employee.getLastName());
        sb.append("Gets: " + calculate() + " Bit Coin(s) after Taxes");
        return sb.toString();
    }
}
