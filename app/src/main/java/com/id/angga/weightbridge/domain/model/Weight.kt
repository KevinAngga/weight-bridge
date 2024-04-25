package com.id.angga.weightbridge.domain.model

data class Weight(
    var id : Int = 0,
    val licenseNumber : String = "",
    val driverName : String = "",
    val inboundWeight : Int = 0,
    val outboundWeight : Int =  0,
    val date : String = "",
    val pushId : String = "",
    val isSync : Boolean = false
) {
    fun nettWeight() : Int = inboundWeight - outboundWeight

//    fun addDate() : String  = SimpleDateFormat("yyyy/MM/dd hh:mm")
//    .format(Date(date))
}
