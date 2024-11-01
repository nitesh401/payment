package com.payment.controller;

import com.payment.dto.PaymentRequestDTO;
import com.payment.dto.PaymentResponseDTO;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        return paymentService.createPayment(paymentRequestDTO);
    }
    @GetMapping("/{id}")
    public PaymentResponseDTO getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}")
    public PaymentResponseDTO updatePayment(@PathVariable Long id, @RequestBody PaymentRequestDTO paymentRequestDTO) {
        return paymentService.updatePayment(id, paymentRequestDTO);
    }
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "Payment Deleted for id: "+id;
    }
}
