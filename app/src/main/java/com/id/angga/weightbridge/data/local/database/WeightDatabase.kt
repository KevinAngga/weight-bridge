package com.id.angga.weightbridge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeightEntity::class], version = 1)
abstract class WeightDatabase : RoomDatabase() {
    abstract fun weightDao() : WeightDao
}