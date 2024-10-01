package com.anderfolg.daorest.entities;

import java.math.BigDecimal;

/**
 * DTO for {@link Payment}
 */
public record PaymentDto(String payerName, Long payerInn,
                         Long payerCardNo,
                         Long recipientAccount, String recipientMfo,
                         Long recipientOkpo,
                         String recipientName,
                         Long paymentPeriod,
                         BigDecimal paymentAmount) {
}