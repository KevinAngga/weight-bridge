package com.id.angga.weightbridge.di

import com.id.angga.weightbridge.domain.usecase.validation.ValidationInboundWeight
import com.id.angga.weightbridge.domain.usecase.validation.ValidationName
import com.id.angga.weightbridge.domain.usecase.validation.ValidationOutboundWeight
import com.id.angga.weightbridge.domain.usecase.validation.ValidationTruckNumber
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {

    @Provides
    @Singleton
    fun provideValidateEmail() : ValidationName {
        return ValidationName()
    }

    @Provides
    @Singleton
    fun provideValidateTruckNumber() : ValidationTruckNumber {
        return ValidationTruckNumber()
    }

    @Provides
    @Singleton
    fun provideValidateInbound() : ValidationInboundWeight {
        return ValidationInboundWeight()
    }

    @Provides
    @Singleton
    fun provideValidateOutbound() : ValidationOutboundWeight {
        return ValidationOutboundWeight()
    }
}