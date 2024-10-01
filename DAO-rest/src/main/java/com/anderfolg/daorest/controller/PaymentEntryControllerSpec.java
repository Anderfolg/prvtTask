package com.anderfolg.daorest.controller;

import com.anderfolg.daorest.entities.PaymentEntry;
import com.anderfolg.daorest.entities.PaymentEntryDto;
import com.anderfolg.daorest.entities.PaymentEntryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Payment Entry", description = "Payment Entry management")
public interface PaymentEntryControllerSpec {

    @Operation(
            summary = "Create payment entry",
            description = "Create a new payment entry",
            tags = {"Payment Entry"},
            responses = {@ApiResponse(responseCode = "201", description = "Payment entry created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentEntryResponseDto.class)))})
    ResponseEntity<PaymentEntry> createPaymentEntry( @RequestBody PaymentEntryDto paymentEntryDTO );

    @Operation(
            summary = "Retrieve Payment Entry by ID",
            description = "Retrieves a payment entry by its unique identifier. The response includes payment entry details such as amount and entry time.",
            tags = {"Payment Entry", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment entry retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentEntryResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Payment entry not found")})
    ResponseEntity<PaymentEntry> readPaymentEntryById( @PathVariable Long id );

    @Operation(
            summary = "Retrieve Payment Entries by Payment ID",
            description = "Retrieves all payment entries associated with a specific payment ID.",
            tags = {"Payment Entry", "getAll"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment entries retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentEntryResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "No payment entries found for the given payment ID")})
    ResponseEntity<List<PaymentEntry>> readPaymentEntriesByPaymentId( @PathVariable Long paymentId );

    @Operation(
            summary = "Update a Payment Entry",
            description = "Updates an existing payment entry with the provided details.",
            tags = {"Payment Entry", "update"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment entry updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentEntryResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., invalid data)"),
                    @ApiResponse(responseCode = "404", description = "Payment entry not found")})
    ResponseEntity<PaymentEntry> updatePaymentEntry( @PathVariable Long id, @RequestBody PaymentEntryDto paymentEntryDTO );

    @Operation(
            summary = "Delete a Payment Entry",
            description = "Deletes an existing payment entry by its ID.",
            tags = {"Payment Entry", "delete"},
            responses = {@ApiResponse(responseCode = "200", description = "Payment entry deleted"),
                    @ApiResponse(responseCode = "404", description = "Payment entry not found")})
    ResponseEntity<Void> deletePaymentEntry( @PathVariable Long id );
}
