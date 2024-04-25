package com.id.angga.weightbridge.di

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.id.angga.weightbridge.data.local.database.WeightDatabase
import com.id.angga.weightbridge.data.repository.WeightRepositoryImpl
import com.id.angga.weightbridge.domain.repository.WeightRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideWeightRepository(
        weightDatabase: WeightDatabase,
        firebaseDatabase: FirebaseDatabase,
        @ApplicationContext context : Context
    ) : WeightRepository {
        return WeightRepositoryImpl(
            context = context,
            database = weightDatabase,
            firebaseDatabase = firebaseDatabase
        )
    }
}