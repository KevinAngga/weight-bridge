package com.id.angga.weightbridge.data.repository


import android.content.Context
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.id.angga.weightbridge.data.local.database.WeightDatabase
import com.id.angga.weightbridge.data.mappers.toWeight
import com.id.angga.weightbridge.data.mappers.toWeightDto
import com.id.angga.weightbridge.data.mappers.toWeightEntity
import com.id.angga.weightbridge.data.mappers.toWeightList
import com.id.angga.weightbridge.data.remote.dto.WeightDto
import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.repository.WeightRepository
import com.id.angga.weightbridge.util.Constant.WEIGHT_DATABASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeightRepositoryImpl @Inject constructor(
    val context: Context,
    val database: WeightDatabase,
    val firebaseDatabase: FirebaseDatabase,
) : WeightRepository {

    override suspend fun getTicketList(): Flow<List<Weight>> {
        return database.weightDao().getAll().map { ticketList ->
            println("-- data base "+ticketList.size)
            ticketList.toWeightList()
        }
    }

    override suspend fun getTicketFromRemote(): List<Weight> {
        val weightList = mutableListOf<Weight>()
        withContext(Dispatchers.IO) {
            println("-- get ticket")
            try {
                val firebaseTask = firebaseDatabase.getReference(WEIGHT_DATABASE).get()
                await(firebaseTask)
                if (firebaseTask.isSuccessful) {
                    val weightMap =
                        firebaseTask.result.getValue<HashMap<String, WeightDto>>() ?: hashMapOf()
                    weightList.addAll(weightMap.values.map { it.toWeight() })
                    println("-- weight list " + weightList)
                }
            } catch (e: Exception) {
                println("-- error " + e.message)
            }
        }
        return weightList
    }

    override suspend fun pushTicketToFirebase(weight: Weight) {
        withContext(Dispatchers.IO) {
            var currentWeight = weight
            currentWeight = currentWeight.copy(
                isSync = true
            )
            val task = firebaseDatabase.getReference(WEIGHT_DATABASE).child(currentWeight.pushId)
                .setValue(currentWeight.toWeightDto())
            await(task)
            if (task.isSuccessful) {
                updateTicket(currentWeight)
            }
        }
    }

    override suspend fun updateTicketToFirebase(weight: Weight) {
        withContext(Dispatchers.IO) {
            var currentWeight = weight
            currentWeight = currentWeight.copy(
                isSync = true
            )
            val updateRef =
                firebaseDatabase.getReference(WEIGHT_DATABASE).child(currentWeight.pushId)
            val task = updateRef.setValue(currentWeight.toWeightDto())
            await(task)
            if (task.isSuccessful) {
                updateTicket(currentWeight)
            }
        }
    }


    override suspend fun getTicket(primaryKey: Int): Weight? {
        return database.weightDao().getSingleById(primaryKey)?.toWeight()
    }

    override suspend fun addTicketToLocal(weight: Weight): Long {
        return database.weightDao().insert(weight.toWeightEntity())
    }

    override suspend fun deleteTicket(weight: Weight) {
        database.weightDao().delete(
            weight.toWeightEntity()
        )
    }

    override suspend fun updateTicket(weight: Weight) {
        return database.weightDao().update(
            weight.toWeightEntity()
        )
    }

    override suspend fun addTicketsToLocal(list: List<Weight>) {
        database.weightDao().deleteAll()
        database.weightDao().addAllTickets(list.toWeightEntity())
        println("-- add them")
    }

}