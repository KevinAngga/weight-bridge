package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.ConnectivityObserver
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateTicket @Inject constructor(
    private val weightRepository: WeightRepository,
    private val networkConnectivityObserver: NetworkConnectivityObserver
){
    suspend fun invoke(weight: Weight) {
       val connectionStatus = networkConnectivityObserver.observer().first()
        weightRepository.updateTicket(weight)
        if (connectionStatus == ConnectivityObserver.Status.Available) {
            weightRepository.updateTicketToFirebase(weight)
        }
    }
}