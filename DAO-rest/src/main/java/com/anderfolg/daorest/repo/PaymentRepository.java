package com.anderfolg.daorest.repo;

import com.anderfolg.daorest.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> getAllByPayerName(String payerName);
    List<Payment> getAllByPayerInn(Long payerInn);
    List<Payment> getAllByRecipientOkpo(Long recipientOkpo);

}

