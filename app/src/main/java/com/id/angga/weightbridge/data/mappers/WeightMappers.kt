package com.id.angga.weightbridge.data.mappers

import com.id.angga.weightbridge.data.local.database.WeightEntity
import com.id.angga.weightbridge.data.remote.dto.WeightDto
import com.id.angga.weightbridge.domain.model.Weight


fun WeightEntity.toWeight() : Weight {
    return Weight(
        id = id,
        licenseNumber = licenseNumber,
        driverName = driverName,
        inboundWeight = inboundWeight,
        outboundWeight = outboundWeight,
        date = date,
        pushId = pushId,
        isSync = isSync
    )
}

fun List<WeightEntity>.toWeightList() : List<Weight> {
    return this.map { ticket ->
        Weight(
            id = ticket.id,
            licenseNumber = ticket.licenseNumber,
            driverName = ticket.driverName,
            inboundWeight = ticket.inboundWeight,
            outboundWeight = ticket.outboundWeight,
            date = ticket.date,
            pushId = ticket.pushId,
            isSync = ticket.isSync
        )
    }
}

fun List<Weight>.toWeightEntity() : List<WeightEntity> {
    return this.map { ticket ->
        WeightEntity(
            id = ticket.id,
            licenseNumber = ticket.licenseNumber,
            driverName = ticket.driverName,
            inboundWeight = ticket.inboundWeight,
            outboundWeight = ticket.outboundWeight,
            date = ticket.date,
            pushId = ticket.pushId,
            isSync = ticket.isSync
        )
    }
}

fun List<WeightDto>.toWeightEntityList() : List<WeightEntity> {
    return this.map { ticket ->
        WeightEntity(
            id = ticket.id,
            licenseNumber = ticket.licenseNumber,
            driverName = ticket.driverName,
            inboundWeight = ticket.inboundWeight,
            outboundWeight = ticket.outboundWeight,
            date = ticket.date,
            pushId = ticket.pushId,
            isSync = ticket.sync
        )
    }
}

fun List<WeightDto>.toWeight() : List<Weight> {
    return this.map { ticket ->
        Weight(
            id = ticket.id,
            licenseNumber = ticket.licenseNumber,
            driverName = ticket.driverName,
            inboundWeight = ticket.inboundWeight,
            outboundWeight = ticket.outboundWeight,
            date = ticket.date,
            pushId = ticket.pushId,
            isSync = ticket.sync
        )
    }
}

fun WeightDto.toWeightEntity() : WeightEntity {
    return WeightEntity(
        id = id,
        licenseNumber = licenseNumber,
        driverName = driverName,
        inboundWeight = inboundWeight,
        outboundWeight = outboundWeight,
        date = date,
        pushId = pushId,
        isSync = sync
    )
}

fun WeightEntity.toWeightDto() : WeightDto {
    return WeightDto(
        id = id,
        licenseNumber = licenseNumber,
        driverName = driverName,
        inboundWeight = inboundWeight,
        outboundWeight = outboundWeight,
        date = date,
        pushId = pushId,
        sync = isSync
    )
}

fun Weight.toWeightDto() : WeightDto {
    return WeightDto(
        id = id,
        licenseNumber = licenseNumber,
        driverName = driverName,
        inboundWeight = inboundWeight,
        outboundWeight = outboundWeight,
        date = date,
        pushId = pushId,
        sync = isSync
    )
}

fun WeightDto.toWeight() : Weight {
    return Weight(
        id = id,
        licenseNumber = licenseNumber,
        driverName = driverName,
        inboundWeight = inboundWeight,
        outboundWeight = outboundWeight,
        date = date,
        pushId = pushId,
        isSync = sync
    )
}

fun Weight.toWeightEntity() : WeightEntity {
    return WeightEntity(
        id = id,
        licenseNumber = licenseNumber,
        driverName = driverName,
        inboundWeight = inboundWeight,
        outboundWeight = outboundWeight,
        date = date,
        pushId = pushId,
        isSync = isSync
    )
}