package com.sedsoftware.nxmods.database.mappers

import com.sedsoftware.nxmods.database.TrackingInfoEntity
import com.sedsoftware.nxmods.domain.entity.TrackingInfo

internal object TrackingInfoEntityMappers {
    val trackingInfoToDomain: TrackingInfoEntity.() -> TrackingInfo = {
        TrackingInfo(
            modId = modId,
            domain = domain
        )
    }

    val trackingInfoToDb: TrackingInfo.() -> TrackingInfoEntity = {
        TrackingInfoEntity(
            id = 0L,
            modId = modId,
            domain = domain
        )
    }
}
