package com.anderfolg.daorest.controller;

import com.anderfolg.daorest.entities.Payment;
import com.anderfolg.daorest.entities.PaymentDto;
import com.anderfolg.daorest.entities.PaymentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Payment", description = "Payment management")
public interface PaymentControllerSpec {

    @Operation(
            summary = "Create payment",
            description = "Create a new payment",
            tags = {"Payment"},
            responses = {@ApiResponse(responseCode = "201", description = "Payment created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class)))} )
    ResponseEntity<Payment> createPayment( @RequestBody PaymentDto paymentDto);

    @Operation(
            summary = "Retrieve All Payments",
            description = "Retrieves a list of all payments.",
            tags = {"Payment", "getAll"},
            responses = {@ApiResponse(responseCode = "200", description = "List of payments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class)))} )
    ResponseEntity<List<Payment>> readAllPayments();

    @Operation(
            summary = "Retrieve a Payment by ID",
            description = "Retrieves a payment by its unique identifier. The response includes payment details such as payer name, recipient name, amount, etc.",
            tags = {"Payment", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Payment not found")} )
    ResponseEntity<Payment> readPaymentById(@PathVariable Long id);

    @Operation(
            summary = "Retrieve Payments by Payer Name",
            description = "Retrieves payments made by a specific payer identified by their name.",
            tags = {"Payment", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found for the payer name")} )
    ResponseEntity<List<Payment>> readPaymentsByPayerName(@PathVariable String payerName);

    @Operation(
            summary = "Retrieve Payments by Payer INN",
            description = "Retrieves payments made by a specific payer identified by their INN.",
            tags = {"Payment", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found for the payer INN")} )
    ResponseEntity<List<Payment>> readPaymentsByPayerInn(@PathVariable Long payerInn);

    @Operation(
            summary = "Retrieve Payments by Recipient OKPO",
            description = "Retrieves payments sent to a specific recipient identified by their OKPO.",
            tags = {"Payment", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Payments retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "No payments found for the recipient OKPO")} )
    ResponseEntity<List<Payment>> readPaymentsByRecipientOkpo( @PathVariable Long recipientOkpo);

    @Operation(
            summary = "Update a Payment",
            description = "Updates an existing payment with the provided details.",
            tags = {"Payment", "update"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., invalid data)"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")} )
    ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody PaymentDto paymentDto);

    @Operation(
            summary = "Delete a Payment",
            description = "Deletes an existing payment by its ID.",
            tags = {"Payment", "delete"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment deleted"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")} )
    ResponseEntity<Void> deletePayment( @PathVariable Long id);
}

