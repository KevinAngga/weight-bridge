package com.id.angga.weightbridge.di

import android.content.Context
import androidx.room.Room
import com.id.angga.weightbridge.data.local.database.WeightDatabase
import com.id.angga.weightbridge.util.Constant.WEIGHT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context : Context
    ) : WeightDatabase {
        return Room.databaseBuilder(
            context,
            WeightDatabase::class.java,
            WEIGHT_DATABASE
        ).build()
    }
}