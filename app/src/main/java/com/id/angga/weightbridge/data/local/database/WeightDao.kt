package com.id.angga.weightbridge.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface WeightDao {

    @Insert
    suspend fun insert(weightEntity: WeightEntity) : Long

    @Update
    suspend fun update(weightEntity: WeightEntity)

    @Delete
    suspend fun delete(weightEntity: WeightEntity)

    @Query("DELETE FROM `weight` WHERE is_sync = 1")
    suspend fun deleteAll()

    @Query("SELECT * FROM `weight`")
    fun getAll(): Flow<List<WeightEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addAllTickets(tickets : List<WeightEntity>)

    @Query("SELECT * FROM `weight` WHERE id = :id")
    suspend fun getSingleById(id: Int): WeightEntity?
}