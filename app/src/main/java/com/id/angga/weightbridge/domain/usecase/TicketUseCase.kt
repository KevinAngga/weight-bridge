package com.id.angga.weightbridge.domain.usecase

class TicketUseCase (
    val addTicket: AddTicket,
    val getTickets: GetTickets,
    val updateTicket: UpdateTicket,
    val getTicket: GetTicket,
    val syncTicket: SyncTicket
)