package com.id.angga.weightbridge.di


import com.id.angga.weightbridge.domain.usecase.AddTicket
import com.id.angga.weightbridge.domain.usecase.GetTicket
import com.id.angga.weightbridge.domain.usecase.GetTickets
import com.id.angga.weightbridge.domain.usecase.SyncTicket
import com.id.angga.weightbridge.domain.usecase.TicketUseCase
import com.id.angga.weightbridge.domain.usecase.UpdateTicket
import com.id.angga.weightbridge.domain.usecase.validation.TicketValidationUseCase
import com.id.angga.weightbridge.domain.usecase.validation.ValidationInboundWeight
import com.id.angga.weightbridge.domain.usecase.validation.ValidationName
import com.id.angga.weightbridge.domain.usecase.validation.ValidationOutboundWeight
import com.id.angga.weightbridge.domain.usecase.validation.ValidationTruckNumber
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Provides
    fun provideTicketUseCase(
        addTicket: AddTicket,
        getTickets: GetTickets,
        updateTicket: UpdateTicket,
        getTicket: GetTicket,
        syncTicket: SyncTicket
    ) : TicketUseCase {
        return TicketUseCase(
            addTicket = addTicket,
            getTickets = getTickets,
            updateTicket = updateTicket,
            getTicket = getTicket,
            syncTicket = syncTicket
        )
    }


    @Provides
    fun provideTicketValidationUseCase(
        validationName: ValidationName,
        validationTruckNumber: ValidationTruckNumber,
        validationInboundWeight: ValidationInboundWeight,
        validationOutboundWeight: ValidationOutboundWeight
    ) : TicketValidationUseCase {
        return TicketValidationUseCase(
            validationTruckNumber = validationTruckNumber,
            validationName = validationName,
            validationInboundWeight = validationInboundWeight,
            validationOutboundWeight = validationOutboundWeight
        )
    }
}