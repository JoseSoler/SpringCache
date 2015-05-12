package com.zanox;

import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by jsoler on 11.05.15.
 */
@Service
public class PayRollService {
    public PayRoll generatePayRoll(Month month, Employee employee) {
        PayRoll payRoll = new PayRoll(month, employee);
        payRoll.calculate();
        return payRoll;
    }
}
