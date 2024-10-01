package com.anderfolg.daorest.controller;

import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;
import com.anderfolg.daorest.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController implements PaymentControllerSpec{
    private final PaymentService paymentService;
    @Override
    @PostMapping
    public ResponseEntity<Payment> createPayment( PaymentDto paymentDto ) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Payment>> readAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Payment> readPaymentById( Long id ) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @Override
    @GetMapping("/payer")
    public ResponseEntity<List<Payment>> readPaymentsByPayerName( String payerName ) {
        return ResponseEntity.ok(paymentService.getAllPaymentsByPayerName(payerName));
    }

    @Override
    @GetMapping("/payerInn")
    public ResponseEntity<List<Payment>> readPaymentsByPayerInn( Long payerInn ) {
        return ResponseEntity.ok(paymentService.getAllPaymentByPayerInn(payerInn));
    }

    @Override
    @GetMapping("/recipientOkpo")
    public ResponseEntity<List<Payment>> readPaymentsByRecipientOkpo( Long recipientOkpo ) {
        return ResponseEntity.ok(paymentService.getAllPaymentByRecipientOkpo(recipientOkpo));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment( Long id, PaymentDto paymentDto ) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment( Long id ) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
