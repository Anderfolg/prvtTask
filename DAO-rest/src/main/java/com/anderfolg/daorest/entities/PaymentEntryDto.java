package com.anderfolg.daorest.entities;

import com.anderfolg.daorest.entities.enums.EntryStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link PaymentEntry}
 */
public record PaymentEntryDto(
        LocalDateTime entryTime,
        Long paymentId,
        BigDecimal amount,
        EntryStatus status) {
}
