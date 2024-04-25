package com.id.angga.weightbridge.domain.usecase.validation

import com.id.angga.weightbridge.R
import com.id.angga.weightbridge.presentation.utils.UiText

class ValidationInboundWeight {
    fun execute(inboundWeight : String) : ValidationResult {
        if (inboundWeight.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.inbound_error)
            )
        }

        if (inboundWeight.toDoubleOrNull() == null || inboundWeight.toDouble() <= 0) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.inbound_weight_format_error)
            )
        }

        return ValidationResult (
            successful = true
        )
    }
}