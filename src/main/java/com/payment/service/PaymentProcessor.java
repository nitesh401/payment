package com.payment.service;

import com.payment.entity.Payment;

public interface PaymentProcessor {
    void processPayment(Payment payment);
}
