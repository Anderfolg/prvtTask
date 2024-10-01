package com.anderfolg.daorest.services;

import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;

import java.util.List;

public interface PaymentService {
    Payment createPayment( PaymentDto paymentDto);
    Payment updatePayment(Long id, PaymentDto paymentDto);
    Payment getPayment(Long id);
    List<Payment> getAllPayments();
    List<Payment> getAllPaymentsByPayerName(String payerName);
    List<Payment> getAllPaymentByPayerInn(Long payerInn);
    List<Payment> getAllPaymentByRecipientOkpo(Long recipientOkpo);
    void deletePayment(Long id);
}
