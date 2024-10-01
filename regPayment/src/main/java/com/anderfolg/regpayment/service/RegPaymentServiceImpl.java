package com.anderfolg.regpayment.service;

import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.regpayment.util.BusinessClientService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RegPaymentServiceImpl implements RegPaymentService {
    private static final String URL_PAYMENT_PREFIX = "/payments";
    private static final String URL_PAYMENT_ENTRY_PREFIX = "/payment-entries";
    private final Gson gson = new Gson();

    @Override
    public PaymentEntry createPaymentEntryByPaymentId( Long paymentId ) {
        log.info("Creating new payment entry by payment id: {}", paymentId);
        String response = BusinessClientService.post(URL_PAYMENT_ENTRY_PREFIX + "/" + paymentId, null);
        return gson.fromJson(response, PaymentEntry.class);
    }

    @Override
    public List<Payment> getAllPayments() {
        log.info("Retrieving all payments");
        String response = BusinessClientService.get(URL_PAYMENT_PREFIX);
        return Arrays.asList(gson.fromJson(response, Payment[].class));
    }

    @Override
    public List<PaymentEntry> getAllEntriesByPaymentId( Long paymentId ) {
        log.info("Retrieving all entries by payment id: {}", paymentId);
        String response = BusinessClientService.get(URL_PAYMENT_ENTRY_PREFIX + "/" + paymentId);
        return Arrays.asList(gson.fromJson(response, PaymentEntry[].class));
    }
}
