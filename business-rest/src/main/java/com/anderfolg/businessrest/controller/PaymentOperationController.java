package com.anderfolg.businessrest.controller;

import com.anderfolg.businessrest.service.PaymentService;
import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;
import com.anderfolg.daorest.entities.PaymentEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentOperationController implements PaymentOperationControllerSpec {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment( PaymentDto paymentDto ) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDto));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> readAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> readPaymentById( @PathVariable Long id ) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping("/payer/{payerName}")
    public ResponseEntity<List<Payment>> readPaymentsByPayerName( @PathVariable String payerName ) {
        return ResponseEntity.ok(paymentService.getAllPaymentsByPayerName(payerName));
    }

    @GetMapping("/payerInn/{payerInn}")
    public ResponseEntity<List<Payment>> readPaymentsByPayerInn( @PathVariable Long payerInn ) {
        return ResponseEntity.ok(paymentService.getAllPaymentByPayerInn(payerInn));
    }

    @GetMapping("/recipientOkpo/{recipientOkpo}")
    public ResponseEntity<List<Payment>> readPaymentsByRecipientOkpo( @PathVariable Long recipientOkpo ) {
        return ResponseEntity.ok(paymentService.getAllPaymentByRecipientOkpo(recipientOkpo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment( @PathVariable Long id, PaymentDto paymentDto ) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment( @PathVariable Long id ) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/payment-entries/{paymentId}")
    public ResponseEntity<PaymentEntry> createPaymentEntryByPaymentId( @PathVariable Long paymentId ) {
        return ResponseEntity.ok(paymentService.createPaymentEntryByPaymentId(paymentId));
    }

    @GetMapping("/payment-entries/{paymentId}")
    public ResponseEntity<List<PaymentEntry>> getAllPaymentEntriesByPaymentId( @PathVariable Long paymentId ) {
        return ResponseEntity.ok(paymentService.getAllPaymentEntriesByPaymentId(paymentId));
    }
}
