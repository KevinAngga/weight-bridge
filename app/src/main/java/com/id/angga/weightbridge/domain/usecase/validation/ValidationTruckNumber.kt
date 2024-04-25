package com.id.angga.weightbridge.domain.usecase.validation

import com.id.angga.weightbridge.R
import com.id.angga.weightbridge.presentation.utils.UiText

class ValidationTruckNumber {
    fun execute(truckLicense : String) : ValidationResult {
        if (truckLicense.length < 3) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.truc_license_error_length)
            )
        }

        val containLetterAndDigits = truckLicense.any { it.isDigit() } &&
                truckLicense.any { it.isLetter() }

        if (!containLetterAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.truc_license_error)
            )
        }

        return ValidationResult (
            successful = true
        )
    }
}