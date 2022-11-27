package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.UserInfo
import com.sedsoftware.nxmods.domain.type.EndorseStatus
import com.sedsoftware.nxmods.network.models.ModInfoModel
import com.sedsoftware.nxmods.network.models.UserInfoModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal object ModInfoModelMapper {

    val modInfoToDomain: ModInfoModel.() -> ModInfo = {
        ModInfo(
            modId = modId,
            gameId = gameId,
            domainName = domainName,
            name = name,
            summary = summary,
            version = version,
            description = description,
            pictureUrl = pictureUrl,
            modDownloads = modDownloads,
            modDownloadsUnique = modDownloadsUnique,
            uid = uid,
            allowRating = allowRating,
            categoryId = categoryId,
            endorsementCount = endorsementCount,
            createdTimestamp = createdTimestamp,
            createdTime = Instant.fromEpochSeconds(createdTimestamp).toLocalDateTime(timeZone = TimeZone.currentSystemDefault()),
            updatedTimestamp = updatedTimestamp,
            updatedTime = Instant.fromEpochSeconds(updatedTimestamp).toLocalDateTime(timeZone = TimeZone.currentSystemDefault()),
            author = author,
            uploadedBy = uploadedBy,
            uploaderProfileUrl = uploaderProfileUrl,
            containsAdultContent = containsAdultContent,
            status = status,
            available = available,
            user = mapUserInfo(user),
            isTracked = false,
            isEndorsed = EndorseStatus.fromStr(endorsement?.status) == EndorseStatus.ENDORSED
        )
    }

    val modInfoListToDomain: List<ModInfoModel>.() -> List<ModInfo> = {
        map { modInfoToDomain(it) }
    }

    private fun mapUserInfo(from: UserInfoModel): UserInfo =
        UserInfo(
            memberId = from.memberId,
            memberGroupId = from.memberGroupId,
            name = from.name
        )
}
