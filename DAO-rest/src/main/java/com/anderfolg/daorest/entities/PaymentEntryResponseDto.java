package com.anderfolg.daorest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class PaymentEntryResponseDto {

        private Long id;
        private String payerName;
        private Long payerInn;
        private Long payerCardNo;
        private Long recipientAccount;
        private String recipientMfo;
        private Long recipientOkpo;
        private String recipientName;
        private Long paymentPeriod;
        private Long paymentAmount;
        private Long entryId;
        private Long entryTime;
        private Long entryAmount;
        private String entryStatus;
}
