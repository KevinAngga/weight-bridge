package com.id.angga.weightbridge.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "weight")
data class WeightEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo("license_number")
    val licenseNumber : String,
    @ColumnInfo("driver_name")
    val driverName : String,
    @ColumnInfo("inbound_weight")
    val inboundWeight : Int,
    @ColumnInfo("outbound_weight")
    val outboundWeight : Int,
    @ColumnInfo("date")
    val date : String,
    @ColumnInfo("push_id")
    val pushId : String,
    @ColumnInfo("is_sync")
    val isSync : Boolean
)