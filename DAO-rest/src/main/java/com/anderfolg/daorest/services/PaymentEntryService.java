package com.anderfolg.daorest.services;

import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.daorest.entities.PaymentEntryDto;

import java.util.List;

public interface PaymentEntryService {
    PaymentEntry createPaymentEntry( PaymentEntryDto paymentEntryDTO);
    PaymentEntry getPaymentEntryById( Long id);
    List<PaymentEntry> getPaymentEntriesByPaymentId( Long paymentId);
    PaymentEntry updatePaymentEntry( Long id, PaymentEntryDto paymentEntryDTO);
    void deletePaymentEntry( Long id);
}
