package com.anderfolg.regpayment.service;

import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentEntry;

import java.util.List;

public interface RegPaymentService {
    PaymentEntry createPaymentEntryByPaymentId( Long paymentId);
    List<Payment> getAllPayments();
    List<PaymentEntry> getAllEntriesByPaymentId( Long paymentId);
}
