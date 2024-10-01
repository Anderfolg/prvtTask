package com.anderfolg.businessrest.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PaymentValidator.class)
public @interface ValidPayment {
    String message() default "Invalid payment dto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
