package com.anderfolg.daorest.repo;

import com.anderfolg.daorest.entities.PaymentEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, Long> {
    List<PaymentEntry> getAllByPaymentId(Long paymentId);
}
