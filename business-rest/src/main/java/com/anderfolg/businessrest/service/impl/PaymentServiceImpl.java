package com.anderfolg.businessrest.service.impl;

import com.anderfolg.businessrest.service.PaymentService;
import com.anderfolg.businessrest.utils.ClientService;
import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;
import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.daorest.entities.enums.EntryStatus;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private static final String URL_PAYMENT_PREFIX = "/payments";
    private static final String URL_PAYMENT_ENTRY_PREFIX = "/payment-entries";
    private final Gson gson = new Gson();

    @Override
    public Payment createPayment( PaymentDto paymentDto ) {
        log.info("Creating new payment");
        String response = ClientService.post(URL_PAYMENT_PREFIX, gson.toJson(paymentDto, PaymentDto.class));
        return gson.fromJson(response, Payment.class);
    }


    @Override
    public Payment updatePayment( Long id, PaymentDto paymentDto ) {
        log.info("Updating payment by id: {}", id);
        String response = ClientService.update(URL_PAYMENT_PREFIX + "/" + id, gson.toJson(paymentDto, Payment.class));
        return gson.fromJson(response, Payment.class);
    }

    @Override
    public Payment getPayment( Long id ) {
        log.info("Retrieving payment by id: {}", id);
        String response = ClientService.get(URL_PAYMENT_PREFIX + "/" + id);
        return gson.fromJson(response, Payment.class);
    }

    @Override
    public List<Payment> getAllPayments() {
        log.info("Retrieving all payments");
        String response = ClientService.get(URL_PAYMENT_PREFIX);
        return Arrays.asList(gson.fromJson(response, Payment[].class));
    }

    @Override
    public List<Payment> getAllPaymentsByPayerName( String payerName ) {
        log.info("Retrieving payments by payer name: {}", payerName);
        String response = ClientService.get(URL_PAYMENT_PREFIX + "/" + payerName);
        return Arrays.asList(gson.fromJson(response, Payment[].class));
    }

    @Override
    public List<Payment> getAllPaymentByPayerInn( Long payerInn ) {
        log.info("Retrieving payments by payer INN: {}", payerInn);
        String response = ClientService.get(URL_PAYMENT_PREFIX + "/" + payerInn);
        return Arrays.asList(gson.fromJson(response, Payment[].class));
    }

    @Override
    public List<Payment> getAllPaymentByRecipientOkpo( Long recipientOkpo ) {
        log.info("Retrieving payments by recipient OKPO: {}", recipientOkpo);
        String response = ClientService.get(URL_PAYMENT_PREFIX + "/" + recipientOkpo);
        return Arrays.asList(gson.fromJson(response, Payment[].class));
    }

    @Override
    public void deletePayment( Long id ) {
        log.info("Deleting payment by id: {}", id);
        ClientService.delete(URL_PAYMENT_PREFIX + "/" + id);
    }

    @Override
    public PaymentEntry createPaymentEntryByPaymentId( Long paymentId ) {
        log.info("Creating new Payment Entry for Payment ID: {}", paymentId);
        Payment payment = getPayment(paymentId);
        if ( payment == null ) {
            throw new IllegalArgumentException("Payment not found for ID: " + paymentId);
        }

        // Check if debiting is required
        List<PaymentEntry> history = getAllPaymentEntriesByPaymentId(paymentId);
        PaymentEntry lastEntry = history.stream()
                .max(Comparator.comparing(PaymentEntry::getEntryTime))
                .orElse(null);

        // Create a new entry if needed based on paymentPeriod and last transaction
        if ( lastEntry == null || lastEntry.getEntryTime().plusDays(payment.getPaymentPeriod()).isBefore(LocalDateTime.now()) ) {
            PaymentEntry paymentEntry = PaymentEntry.builder()
                    .entryTime(LocalDateTime.now())
                    .payment(payment)
                    .amount(payment.getPaymentAmount())
                    .status(EntryStatus.ACTIVE)
                    .build();

            log.info("Creating new Payment Entry: {}", paymentEntry);
            return createPaymentEntry(paymentEntry);
        }

        log.info("No need to create a new Payment Entry. Last entry is within period.");
        return null;
    }

    private PaymentEntry createPaymentEntry( PaymentEntry paymentEntry ) {
        String response = ClientService.post(URL_PAYMENT_ENTRY_PREFIX, gson.toJson(paymentEntry, PaymentEntry.class));
        return gson.fromJson(response, PaymentEntry.class);
    }

    @Override
    public List<PaymentEntry> getAllPaymentEntriesByPaymentId( Long paymentId ) {
        log.info("Retrieving all Payment Entries by Payment ID: {}", paymentId);
        String response = ClientService.get(URL_PAYMENT_ENTRY_PREFIX + "/" + paymentId);
        return Arrays.asList(gson.fromJson(response, PaymentEntry[].class));
    }
}
