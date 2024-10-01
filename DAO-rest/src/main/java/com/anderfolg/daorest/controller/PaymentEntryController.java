package com.anderfolg.daorest.controller;

import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.daorest.entities.PaymentEntryDto;
import com.anderfolg.daorest.services.PaymentEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-entries")
public class PaymentEntryController implements PaymentEntryControllerSpec{
    private final PaymentEntryService paymentEntryService;
    @Override
    @PostMapping
    public ResponseEntity<PaymentEntry> createPaymentEntry( PaymentEntryDto paymentEntryDTO ) {
        return ResponseEntity.ok(paymentEntryService.createPaymentEntry(paymentEntryDTO));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntry> readPaymentEntryById( Long id ) {
        return ResponseEntity.ok(paymentEntryService.getPaymentEntryById(id));
    }

    @Override
    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<PaymentEntry>> readPaymentEntriesByPaymentId( Long paymentId ) {
        return ResponseEntity.ok(paymentEntryService.getPaymentEntriesByPaymentId(paymentId));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PaymentEntry> updatePaymentEntry( Long id, PaymentEntryDto paymentEntryDTO ) {
        return ResponseEntity.ok(paymentEntryService.updatePaymentEntry(id, paymentEntryDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentEntry( Long id ) {
        paymentEntryService.deletePaymentEntry(id);
        return ResponseEntity.ok().build();
    }
}
