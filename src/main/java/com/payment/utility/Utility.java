package com.payment.utility;

import com.payment.dto.PaymentRequestDTO;
import com.payment.enums.PaymentMode;
import com.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class Utility {
    @Autowired
    private PaymentRepository paymentRepository;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    public boolean isValidPaymentMode(String paymentMode) {
        try {
            PaymentMode.valueOf(paymentMode.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public void validatePaymentRequest(PaymentRequestDTO paymentRequestDTO, boolean isCreating) {

        if (!isValidEmail(paymentRequestDTO.getUserEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (isCreating && paymentRepository.existsByUserEmail(paymentRequestDTO.getUserEmail())) {
            throw new IllegalArgumentException("Payment already exists for this email ID: " + paymentRequestDTO.getUserEmail());
        }

        if (!isValidPaymentMode(String.valueOf(paymentRequestDTO.getPaymentMode()))) {
            throw new IllegalArgumentException("Invalid payment mode: " + paymentRequestDTO.getPaymentMode());
        }
    }

}
