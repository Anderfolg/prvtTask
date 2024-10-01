package com.anderfolg.daorest.services.impl;

import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.daorest.entities.PaymentEntryDto;
import com.anderfolg.daorest.repo.PaymentEntryRepository;
import com.anderfolg.daorest.services.PaymentEntryService;
import com.anderfolg.daorest.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEntryServiceImpl implements PaymentEntryService {
    private final PaymentEntryRepository paymentEntryRepository;
    private final PaymentService paymentService;

    @Override
    public PaymentEntry createPaymentEntry( PaymentEntryDto paymentEntryDTO ) {
        log.info("Creating new payment entry");

        return PaymentEntry.builder()
                .payment(paymentService.getPayment(paymentEntryDTO.paymentId()))
                .amount(paymentEntryDTO.amount())
                .entryTime(paymentEntryDTO.entryTime())
                .build();
    }

    @Override
    public PaymentEntry getPaymentEntryById( Long id ) {
        log.info("Retrieving payment entry by id: {}", id);
        return paymentEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment entry not found"));
    }

    @Override
    public List<PaymentEntry> getPaymentEntriesByPaymentId( Long paymentId ) {
        log.info("Retrieving payment entries by payment id: {}", paymentId);
        return paymentEntryRepository.getAllByPaymentId(paymentId);
    }

    @Override
    public PaymentEntry updatePaymentEntry( Long id, PaymentEntryDto paymentEntryDTO ) {
        log.info("Updating payment entry by id: {}", id);
        PaymentEntry paymentEntryToUpdate = getPaymentEntryById(id);
        paymentEntryToUpdate.setAmount(paymentEntryDTO.amount());
        paymentEntryToUpdate.setEntryTime(paymentEntryDTO.entryTime());
        paymentEntryToUpdate.setPayment(paymentService.getPayment(paymentEntryDTO.paymentId()));
        return paymentEntryRepository.save(paymentEntryToUpdate);
    }

    @Override
    public void deletePaymentEntry( Long id ) {
        log.info("Deleting payment entry by id: {}", id);
        paymentEntryRepository.deleteById(id);
    }
}
