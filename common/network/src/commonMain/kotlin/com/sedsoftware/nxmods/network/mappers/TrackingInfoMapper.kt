package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.network.models.TrackingInfoModel

internal object TrackingInfoMapper {

    val trackingInfoToDomain: TrackingInfoModel.() -> TrackingInfo = {
        TrackingInfo(
            domain = domain,
            modId = modId
        )
    }

    val trackingInfoListToDomain: List<TrackingInfoModel>.() -> List<TrackingInfo> = {
        map { trackingInfoToDomain(it) }
    }
}
