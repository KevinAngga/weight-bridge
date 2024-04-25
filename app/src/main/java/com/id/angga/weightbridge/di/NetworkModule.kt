package com.id.angga.weightbridge.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.id.angga.weightbridge.util.Constant.BASE_URL
import com.id.angga.weightbridge.util.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabase() : FirebaseDatabase {
        return Firebase.database(BASE_URL)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ) : NetworkConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}