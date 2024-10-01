package com.anderfolg.daorest.services.impl;

import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;
import com.anderfolg.daorest.repo.PaymentRepository;
import com.anderfolg.daorest.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(PaymentDto paymentDto) {
        log.info("Creating new payment");
        Payment payment = mapToPayment(paymentDto);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        log.info("Retrieving payment by id: {}", id);
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public List<Payment> getAllPayments() {
        log.info("Retrieving all payments");
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getAllPaymentsByPayerName(String payerName) {
        log.info("Retrieving all payments by payer name: {}", payerName);
        return paymentRepository.getAllByPayerName(payerName);
    }

    @Override
    public List<Payment> getAllPaymentByPayerInn(Long payerInn) {
        log.info("Retrieving all payments by payer INN: {}", payerInn);
        return paymentRepository.getAllByPayerInn(payerInn);
    }

    @Override
    public List<Payment> getAllPaymentByRecipientOkpo(Long recipientOkpo) {
        log.info("Retrieving all payments by recipient OKPO: {}", recipientOkpo);
        return paymentRepository.getAllByRecipientOkpo(recipientOkpo);
    }

    @Override
    public Payment updatePayment(Long id, PaymentDto paymentDto) {
        log.info("Updating payment by id: {}", id);
        Payment paymentToUpdate = getPayment(id);
        updatePaymentDetails(paymentToUpdate, paymentDto);
        return paymentRepository.save(paymentToUpdate);
    }

    @Override
    public void deletePayment(Long id) {
        log.info("Deleting payment by id: {}", id);
        Payment paymentToDelete = getPayment(id);
        paymentRepository.delete(paymentToDelete);
    }

    // Helper method to map PaymentDto to Payment
    private Payment mapToPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .payerName(paymentDto.payerName())
                .payerInn(paymentDto.payerInn())
                .payerCardNo(paymentDto.payerCardNo())
                .recipientAccount(paymentDto.recipientAccount())
                .recipientMfo(paymentDto.recipientMfo())
                .recipientOkpo(paymentDto.recipientOkpo())
                .recipientName(paymentDto.recipientName())
                .paymentPeriod(paymentDto.paymentPeriod())
                .paymentAmount(paymentDto.paymentAmount())
                .build();
    }

    // Helper method to update Payment details
    private void updatePaymentDetails(Payment payment, PaymentDto paymentDto) {
        payment.setPayerName(paymentDto.payerName());
        payment.setPayerInn(paymentDto.payerInn());
        payment.setPayerCardNo(paymentDto.payerCardNo());
        payment.setRecipientAccount(paymentDto.recipientAccount());
        payment.setRecipientMfo(paymentDto.recipientMfo());
        payment.setRecipientOkpo(paymentDto.recipientOkpo());
        payment.setRecipientName(paymentDto.recipientName());
        payment.setPaymentPeriod(paymentDto.paymentPeriod());
        payment.setPaymentAmount(paymentDto.paymentAmount());
    }
}
