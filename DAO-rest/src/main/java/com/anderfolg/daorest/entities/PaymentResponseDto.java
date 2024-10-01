package com.anderfolg.daorest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private Long id;
    private String payerName;
    private Long payerInn;
    private Long payerCardNo;
    private Long recipientAccount;
    private String recipientMfo;
    private Long recipientOkpo;
    private String recipientName;
    private Long paymentPeriod;
    private BigDecimal paymentAmount;
}

