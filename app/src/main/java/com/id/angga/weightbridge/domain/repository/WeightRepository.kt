package com.id.angga.weightbridge.domain.repository

import com.id.angga.weightbridge.domain.model.Weight
import kotlinx.coroutines.flow.Flow


interface WeightRepository {
    suspend fun getTicketList() : Flow<List<Weight>>
    suspend fun getTicketFromRemote() : List<Weight>
    suspend fun pushTicketToFirebase(weight: Weight)
    suspend fun updateTicketToFirebase(weight: Weight)
    suspend fun getTicket(primaryKey: Int) : Weight?
    suspend fun addTicketToLocal(weight: Weight) : Long
    suspend fun deleteTicket(weight: Weight)
    suspend fun updateTicket(weight: Weight)
    suspend fun addTicketsToLocal(list : List<Weight>)
}