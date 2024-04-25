package com.id.angga.weightbridge.domain.usecase.validation

import com.id.angga.weightbridge.R
import com.id.angga.weightbridge.presentation.utils.UiText

class ValidationName {
    fun execute(name : String) : ValidationResult {
        if (name.length < 3) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.name_length_error)
            )
        }

        return ValidationResult (
            successful = true
        )
    }
}