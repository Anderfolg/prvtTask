package com.anderfolg.businessrest.utils;

import com.anderfolg.daorest.entities.PaymentDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class PaymentValidator implements ConstraintValidator<ValidPayment, PaymentDto> {
    @Override
    public boolean isValid( PaymentDto paymentDto, ConstraintValidatorContext constraintValidatorContext ) {
        try {
            validate(paymentDto);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void validate( PaymentDto paymentDto ) {
        requireNonEmpty(paymentDto.payerName(), "Payer name");
        validateINN(paymentDto.payerInn());
        validateCardNumber(paymentDto.payerCardNo());
        requirePositive(paymentDto.recipientAccount(), "Recipient account");
        validateMFO(paymentDto.recipientMfo());
        validateOKPO(paymentDto.recipientOkpo());
        requireNonEmpty(paymentDto.recipientName(), "Recipient name");
        requirePositive(paymentDto.paymentPeriod(), "Payment period");
        requirePositive(paymentDto.paymentAmount(), "Payment amount");
    }

    private void requireNonEmpty( String value, String fieldName ) {
        if ( isNullOrEmpty(value) ) {
            throw new IllegalArgumentException(fieldName + " must not be empty");
        }
    }

    private void requirePositive( Number value, String fieldName ) {
        if ( Optional.ofNullable(value).map(Number::doubleValue).orElse(0D) <= 0 ) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    private void validateFieldLength( String value, int expectedLength, String fieldName ) {
        if ( value.length() != expectedLength ) {
            throw new IllegalArgumentException(fieldName + " must be " + expectedLength + " characters long");
        }
    }

    private void validateMFO( String mfo ) {
        requireNonEmpty(mfo, "MFO");
        validateFieldLength(mfo, 6, "MFO");
    }

    private void validateINN( Long inn ) {
        requirePositive(inn, "INN");
        validateFieldLength(String.valueOf(inn), 10, "INN");
    }

    private void validateCardNumber( Long cardNumber ) {
        requirePositive(cardNumber, "Card number");
        validateFieldLength(String.valueOf(cardNumber), 16, "Card number");
    }

    private void validateOKPO( Long okpo ) {
        requirePositive(okpo, "OKPO");
        validateFieldLength(String.valueOf(okpo), 8, "OKPO");
    }

    private boolean isNullOrEmpty( String value ) {
        return value == null || value.isEmpty();
    }
}
