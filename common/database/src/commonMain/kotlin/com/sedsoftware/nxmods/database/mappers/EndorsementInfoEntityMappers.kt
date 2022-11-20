package com.sedsoftware.nxmods.database.mappers

import com.sedsoftware.nxmods.database.EndorsementInfoEntity
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.type.EndorseStatus

internal object EndorsementInfoEntityMappers {
    val endorseInfoToDomain: EndorsementInfoEntity.() -> EndorsementInfo = {
        EndorsementInfo(
            modId = modId,
            domain = domain,
            status = EndorseStatus.fromStr(status)
        )
    }

    val endorseInfoToDb: EndorsementInfo.() -> EndorsementInfoEntity = {
        EndorsementInfoEntity(
            id = 0L,
            modId = modId,
            domain = domain,
            status = status.label
        )
    }
}
