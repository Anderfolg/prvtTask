package com.anderfolg.daorest.entities;

import com.anderfolg.daorest.entities.enums.EntryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "entry")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="entry_time")
    private LocalDateTime entryTime;
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "status")
    private EntryStatus status;
}
