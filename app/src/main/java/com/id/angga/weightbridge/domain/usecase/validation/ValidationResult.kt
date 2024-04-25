package com.id.angga.weightbridge.domain.usecase.validation

import com.id.angga.weightbridge.presentation.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)