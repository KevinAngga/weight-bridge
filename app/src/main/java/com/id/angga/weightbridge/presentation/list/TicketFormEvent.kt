package com.id.angga.weightbridge.presentation.list

import com.id.angga.weightbridge.domain.model.Weight


sealed class TicketFormEvent {
    data class LicenseNumberChanged(val licenseNumber : String) : TicketFormEvent()
    data class DriverNameChanged(val driverName : String) : TicketFormEvent()
    data class InboundWeightChanged(val inbound : String) : TicketFormEvent()
    data class OutboundWeightChanged(val outbound : String) : TicketFormEvent()
    data class SyncDataWithFirebase(val unSendList : List<Weight>) : TicketFormEvent()
    object  SubmitTicket : TicketFormEvent()
    data class  EditTicket(val weight: Weight) : TicketFormEvent()
    object AddTicket : TicketFormEvent()
    data class FilterTicket(val query : String) : TicketFormEvent()
}