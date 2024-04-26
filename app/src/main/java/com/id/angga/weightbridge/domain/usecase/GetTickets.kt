package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.ConnectivityObserver
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTickets @Inject constructor(
    private val weightRepository: WeightRepository,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    suspend fun invoke(): Flow<List<Weight>> {
        return connectivityObserver.observer().map {
            if (it == ConnectivityObserver.Status.Available) {
                val remote = weightRepository.getTicketFromRemote()
                weightRepository.addTicketsToLocal(remote)

            }
            weightRepository.getTicketList()
        }.first()
    }
}