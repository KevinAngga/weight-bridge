package com.id.angga.weightbridge.domain.usecase

import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.ConnectivityObserver
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTickets @Inject constructor(
    private val weightRepository: WeightRepository,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    suspend fun invoke(): Flow<List<Weight>> {
        println("--- invoke")
        val localData = weightRepository.getTicketList()
        return connectivityObserver.observer().map {
            if (it == ConnectivityObserver.Status.Available) {
                println("--- connection "+it)
                val remote = weightRepository.getTicketFromRemote()
                weightRepository.addTicketsToLocal(remote)

            }
            weightRepository.getTicketList()
        }.first()

//        println("--- invoke")
//        val connectivityStatus = connectivityObserver.observer().first()
//        println("--- connection "+connectivityStatus)
//        if (connectivityStatus == ConnectivityObserver.Status.Available) {
//            val remote = weightRepository.getTicketFromRemote()
//            weightRepository.addTicketsToLocal(remote)
//        }
//        println("--- return")
//        return weightRepository.getTicketList()
    }
}