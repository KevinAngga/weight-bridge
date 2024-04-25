package com.id.angga.weightbridge.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeightDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("license_number")
    val licenseNumber : String,
    @SerializedName("driver_name")
    val driverName : String,
    @SerializedName("inbound_weight")
    val inboundWeight : Int,
    @SerializedName("outbound_weight")
    val outboundWeight : Int,
    @SerializedName("date")
    val date : String = "",
    @SerializedName("push_id")
    val pushId : String,
    @SerializedName("is_sync")
    val sync : Boolean
) {
    constructor() : this(0, "", "", 0, 0, "", "", false)
}
