package com.payment.service;

import com.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class DebitCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing Debit Card payment for user: " + payment.getUserEmail());
    }
}
