package com.payment.service;

import com.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing UPI payment for user: " + payment.getUserEmail());
    }
}
