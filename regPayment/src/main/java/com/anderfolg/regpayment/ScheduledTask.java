package com.anderfolg.regpayment;

import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.regpayment.service.RegPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledTask {
    private final RegPaymentService regPaymentService;

    private static final String DELAY = "30000";

    @Scheduled ( fixedRateString = DELAY)
    public void scheduleTaskWithFixedRate() {
        log.info("Scheduled task with fixed rate");
        regPaymentService.getAllPayments().forEach(payment -> {
            List<PaymentEntry> paymentEntries = regPaymentService.getAllEntriesByPaymentId(payment.getId());
            PaymentEntry lastPaymentEntry = findLastPaymentEntry(paymentEntries);
            if ( lastPaymentEntry == null || lastPaymentEntry.getEntryTime().plusDays(payment.getPaymentPeriod()).isBefore(LocalDateTime.now()) ) {
                regPaymentService.createPaymentEntryByPaymentId(payment.getId());
            } else {
                log.info("Payment with id {} is already paid", payment.getId());
            }
        });
    }

    private PaymentEntry findLastPaymentEntry( List<PaymentEntry> paymentEntries ) {
        log.info("Finding last payment entry");
        return paymentEntries.stream()
                .max(Comparator.comparing(PaymentEntry::getEntryTime))
                .orElseThrow(() -> new RuntimeException("No payment entries found"));
    }
}