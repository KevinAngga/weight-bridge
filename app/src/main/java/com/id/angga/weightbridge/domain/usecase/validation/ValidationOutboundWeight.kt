package com.id.angga.weightbridge.domain.usecase.validation

import com.id.angga.weightbridge.R
import com.id.angga.weightbridge.presentation.utils.UiText

class ValidationOutboundWeight {
    fun execute(inboundWeight : String, outboundWeight : String) : ValidationResult {
        if (outboundWeight.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.outbound_error)
            )
        }

        if (outboundWeight.toDoubleOrNull() == null || outboundWeight.toDouble() <= 0) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.outbound_weight_format_error)
            )
        }

        if (outboundWeight.toDouble() >= inboundWeight.toDouble()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.outbound_weight_less_error)
            )
        }

        return ValidationResult (
            successful = true
        )
    }
}