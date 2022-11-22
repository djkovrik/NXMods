package com.sedsoftware.nxmods.database.mappers

import com.sedsoftware.nxmods.database.EndorsementInfoEntity
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo

internal object EndorsementInfoEntityMappers {
    val endorseInfoToDomain: EndorsementInfoEntity.() -> EndorsementInfo = {
        EndorsementInfo(
            modId = modId,
            domain = domain
        )
    }

    val endorseInfoToDb: EndorsementInfo.() -> EndorsementInfoEntity = {
        EndorsementInfoEntity(
            id = 0L,
            modId = modId,
            domain = domain
        )
    }
}
