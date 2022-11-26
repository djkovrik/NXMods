package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.network.models.EndorsementInfoModel

internal object EndorsementInfoMapper {

    val endorsementInfoToDomain: EndorsementInfoModel.() -> EndorsementInfo = {
        EndorsementInfo(
            modId = modId,
            domain = domain
        )
    }

    val endorsementInfoListToDomain: List<EndorsementInfoModel>.() -> List<EndorsementInfo> = {
        map { endorsementInfoToDomain(it) }
    }
}
