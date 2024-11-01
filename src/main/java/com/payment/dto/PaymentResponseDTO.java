package com.payment.dto;

import com.payment.enums.PaymentMode;
import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long id;
    private Double paymentAmount;
    private String paymentCurrency;
    private String userEmail;
    private PaymentMode paymentMode;
}
