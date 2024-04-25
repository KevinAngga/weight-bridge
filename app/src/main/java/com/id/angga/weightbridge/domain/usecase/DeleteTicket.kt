package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import javax.inject.Inject

class DeleteTicket @Inject constructor(
    private val weightRepository: WeightRepository
){
    suspend fun invoke(weight: Weight) {
        weightRepository.deleteTicket(weight)
    }
}