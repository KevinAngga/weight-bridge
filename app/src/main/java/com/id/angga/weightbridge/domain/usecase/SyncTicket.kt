package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.ConnectivityObserver
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SyncTicket @Inject constructor(
    private val weightRepository: WeightRepository,
    private val networkConnectivityObserver: NetworkConnectivityObserver
) {
    suspend fun invoke(unSendList : List<Weight>) {
        val connection = networkConnectivityObserver.observer().first()
        unSendList.forEach { weight ->
            if (connection == ConnectivityObserver.Status.Available) {
                weightRepository.pushTicketToFirebase(weight)
            }
        }
    }
}