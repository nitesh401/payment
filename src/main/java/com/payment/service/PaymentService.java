package com.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.dto.PaymentRequestDTO;
import com.payment.dto.PaymentResponseDTO;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;
    private final Utility utility;
    private final ObjectMapper objectMapper;
    private final PaymentProcessorFactory paymentProcessorFactory;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          Utility utility,
                          ObjectMapper objectMapper,
                          PaymentProcessorFactory paymentProcessorFactory) {
        this.paymentRepository = paymentRepository;
        this.utility = utility;
        this.objectMapper = objectMapper;
        this.paymentProcessorFactory = paymentProcessorFactory;
    }
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        logger.info("Creating payment for user: {}", paymentRequestDTO.getUserEmail());
        utility.validatePaymentRequest(paymentRequestDTO, true);
        Payment payment = objectMapper.convertValue(paymentRequestDTO, Payment.class);
        PaymentProcessor processor = paymentProcessorFactory.getProcessor(payment.getPaymentMode());
        processor.processPayment(payment);
        payment = paymentRepository.save(payment);
        return objectMapper.convertValue(payment, PaymentResponseDTO.class);
    }
    public PaymentResponseDTO getPaymentById(Long id) {
        logger.info("Fetch the payment for id: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + id));
        return objectMapper.convertValue(payment, PaymentResponseDTO.class);
    }
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO) {
        logger.info("Updating payment with ID: {}", id);
        utility.validatePaymentRequest(paymentRequestDTO, false);
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Payment not found with ID: {}", id);
                    return new IllegalArgumentException("Payment not found with ID: " + id);
                });
        existingPayment.setPaymentAmount(paymentRequestDTO.getPaymentAmount());
        existingPayment.setPaymentCurrency(paymentRequestDTO.getPaymentCurrency());
        existingPayment.setUserEmail(paymentRequestDTO.getUserEmail());
        existingPayment.setPaymentMode(paymentRequestDTO.getPaymentMode());

        existingPayment = paymentRepository.save(existingPayment);
        logger.info("Payment updated successfully with ID: {}", existingPayment.getId());
        return objectMapper.convertValue(existingPayment, PaymentResponseDTO.class);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new IllegalArgumentException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
