package com.payment.dto;

import com.payment.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private Double paymentAmount;
    private String paymentCurrency;
    private String userEmail;
    private PaymentMode paymentMode;
}
