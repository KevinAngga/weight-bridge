package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.ConnectivityObserver
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetTicket @Inject constructor(
    private val weightRepository: WeightRepository
) {
    suspend fun invoke (primaryKey : Int) : Weight {
       return weightRepository.getTicket(primaryKey) ?: Weight()
    }
}

