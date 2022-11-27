package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.network.mappers.TrackingInfoMapper.trackingInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.TrackingInfoMapper.trackingInfoToDomain
import com.sedsoftware.nxmods.network.models.TrackingInfoModel
import com.sedsoftware.nxmods.network.stubs.responses.GetTracked
import kotlin.test.Test
import kotlin.test.assertTrue

class TrackingInfoMapperTest {

    @Test
    fun trackingInfoMapping_test() {
        val response: List<TrackingInfoModel> = GetTracked.response
        val first: TrackingInfoModel = response[0]
        val mapped = trackingInfoToDomain(first)

        assertTrue(mapped.domain.isNotEmpty(), "domain should be mapped")
        assertTrue(mapped.modId != 0L, "modId should be mapped")
    }

    @Test
    fun trackingInfoMappingList_test() {
        val response: List<TrackingInfoModel> = GetTracked.response
        val mapped: List<TrackingInfo> = trackingInfoListToDomain(response)

        assertTrue(mapped.isNotEmpty(), "mapped list should not be empty")

        mapped.forEach { item ->
            assertTrue(item.domain.isNotEmpty(), "domain should be mapped")
            assertTrue(item.modId != 0L, "modId should be mapped")
        }
    }
}
