package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.network.mappers.EndorsementInfoMapper.endorsementInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.EndorsementInfoMapper.endorsementInfoToDomain
import com.sedsoftware.nxmods.network.models.EndorsementInfoModel
import com.sedsoftware.nxmods.network.stubs.responses.GetEndorsed
import kotlin.test.Test
import kotlin.test.assertTrue

class EndorsementInfoMapperTest {

    @Test
    fun endorsementInfoMapping_test() {
        val response: List<EndorsementInfoModel> = GetEndorsed.response
        val single: EndorsementInfoModel = response[0]
        val mapped: EndorsementInfo = endorsementInfoToDomain(single)

        assertTrue(mapped.domain.isNotEmpty(), "Domain should be mapped")
        assertTrue(mapped.modId != 0L, "isNotEmpty should be mapped")
    }

    @Test
    fun endorsementInfoMappingList_test() {
        val response: List<EndorsementInfoModel> = GetEndorsed.response
        val mapped: List<EndorsementInfo> = endorsementInfoListToDomain(response)

        mapped.forEach { item ->
            assertTrue(item.domain.isNotEmpty(), "Domain should be mapped")
            assertTrue(item.modId != 0L, "isNotEmpty should be mapped")
        }
    }
}
