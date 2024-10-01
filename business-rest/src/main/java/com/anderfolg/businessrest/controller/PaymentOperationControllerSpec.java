package com.anderfolg.businessrest.controller;

import com.anderfolg.businessrest.utils.ValidPayment;
import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;
import com.anderfolg.daorest.entities.PaymentEntry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Payments", description = "Payment management operations")
public interface PaymentOperationControllerSpec {

    @Operation(
            summary = "Create a Payment",
            description = "Creates a new payment with the provided details.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Payment created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    ResponseEntity<Payment> createPayment( @RequestBody @ValidPayment PaymentDto paymentDto );

    @Operation(
            summary = "Retrieve all Payments",
            description = "Returns a list of all payments in the system.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found")
            }
    )
    ResponseEntity<List<Payment>> readAllPayments();

    @Operation(
            summary = "Retrieve Payment by ID",
            description = "Retrieves a payment by its unique identifier.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            }
    )
    ResponseEntity<Payment> readPaymentById( @PathVariable Long id );

    @Operation(
            summary = "Retrieve Payments by Payer Name",
            description = "Returns all payments associated with the provided payer name.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found for the provided payer name")
            }
    )
    ResponseEntity<List<Payment>> readPaymentsByPayerName( @PathVariable String payerName );

    @Operation(
            summary = "Retrieve Payments by Payer INN",
            description = "Returns all payments associated with the provided payer INN.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found for the provided payer INN")
            }
    )
    ResponseEntity<List<Payment>> readPaymentsByPayerInn( @PathVariable Long payerInn );

    @Operation(
            summary = "Retrieve Payments by Recipient OKPO",
            description = "Returns all payments associated with the provided recipient OKPO.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found for the provided recipient OKPO")
            }
    )
    ResponseEntity<List<Payment>> readPaymentsByRecipientOkpo( @PathVariable Long recipientOkpo );

    @Operation(
            summary = "Update a Payment",
            description = "Updates an existing payment with the provided details.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            }
    )
    ResponseEntity<Payment> updatePayment( @PathVariable Long id, @RequestBody @ValidPayment PaymentDto paymentDto );

    @Operation(
            summary = "Delete a Payment",
            description = "Deletes an existing payment by its unique identifier.",
            tags = {"Payments"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment deleted"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            }
    )
    ResponseEntity<Void> deletePayment( @PathVariable Long id );

    @Operation(
            summary = "Create a Payment Entry by Payment ID",
            description = "Creates a new payment entry associated with the given payment ID.",
            tags = {"Payment Entries"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Payment entry created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentEntry.class))),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            }
    )
    ResponseEntity<PaymentEntry> createPaymentEntryByPaymentId( @PathVariable Long paymentId );

    @Operation(
            summary = "Retrieve all Payment Entries by Payment ID",
            description = "Returns all payment entries associated with the given payment ID.",
            tags = {"Payment Entries"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of payment entries retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentEntry.class))),
                    @ApiResponse(responseCode = "404", description = "No payment entries found for the given payment ID")
            }
    )
    ResponseEntity<List<PaymentEntry>> getAllPaymentEntriesByPaymentId( @PathVariable Long paymentId );
}

