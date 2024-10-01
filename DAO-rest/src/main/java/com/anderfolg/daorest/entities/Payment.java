package com.anderfolg.daorest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payer_name", nullable = false)
    private String payerName;

    @Column(name = "payer_inn", nullable = false)
    private Long payerInn;

    @Column(name = "payer_card_no", nullable = false)
    private Long payerCardNo;

    @Column(name = "recipient_account", nullable = false)
    private Long recipientAccount;

    @Column(name = "recipient_mfo", nullable = false)
    private String recipientMfo;

    @Column(name = "recipient_okpo", nullable = false)
    private Long recipientOkpo;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Column(name = "payment_period", nullable = false)
    private Long paymentPeriod;

    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;
}