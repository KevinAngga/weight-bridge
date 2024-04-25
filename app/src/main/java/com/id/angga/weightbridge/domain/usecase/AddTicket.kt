package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.ConnectivityObserver
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddTicket @Inject constructor(
    private val weightRepository: WeightRepository,
    private val networkConnectivityObserver: NetworkConnectivityObserver
) {
    suspend fun invoke(weight: Weight)  {
        val inputId = weightRepository.addTicketToLocal(weight)
        val current = weight
        val connectivityStatus = networkConnectivityObserver.observer().first()
        current.id = inputId.toInt()
        if (connectivityStatus == ConnectivityObserver.Status.Available) {
            weightRepository.pushTicketToFirebase(current)
        }
    }
}