package com.payment.entity;

import com.payment.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double paymentAmount;
    private String paymentCurrency;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
}
