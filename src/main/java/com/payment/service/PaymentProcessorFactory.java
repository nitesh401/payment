package com.payment.service;

import com.payment.enums.PaymentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorFactory {
    private final Map<PaymentMode, PaymentProcessor> processorMap;

    @Autowired
    public PaymentProcessorFactory(UpiPaymentProcessor upiProcessor,
                                   NetBankingPaymentProcessor netBankingProcessor,
                                   DebitCardPaymentProcessor debitCardProcessor) {
        this.processorMap = Map.of(
                PaymentMode.UPI, upiProcessor,
                PaymentMode.NETBANKING, netBankingProcessor,
                PaymentMode.DEBIT_CARD, debitCardProcessor
        );
    }

    public PaymentProcessor getProcessor(PaymentMode mode) {
        return processorMap.get(mode);
    }
}
