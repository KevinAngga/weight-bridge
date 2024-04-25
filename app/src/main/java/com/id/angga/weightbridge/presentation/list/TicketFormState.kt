package com.id.angga.weightbridge.presentation.list

import com.id.angga.weightbridge.presentation.utils.UiText


data class TicketFormState (
    val id : Int = 0,
    val licenseNumber : String = "",
    val licenseNumberError : UiText? = null,
    val driverName : String = "",
    val driverNameError : UiText? = null,
    val inboundWeight : String = "",
    val inboundWeightError : UiText? = null,
    val outboundWeight : String = "",
    val outboundWeightError : UiText? = null,
    val buttonEnable : Boolean = false,
    val isUpdate : Boolean = false,
    val filterQuery : String = "",
)