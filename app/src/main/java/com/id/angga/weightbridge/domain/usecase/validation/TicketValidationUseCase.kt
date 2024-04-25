package com.id.angga.weightbridge.domain.usecase.validation

data class TicketValidationUseCase(
    val validationName: ValidationName,
    val validationTruckNumber: ValidationTruckNumber,
    val validationOutboundWeight: ValidationOutboundWeight,
    val validationInboundWeight: ValidationInboundWeight
)
