package com.sedsoftware.nxmods.domain.exception

// Mods
class GetChangelogException(cause: Throwable) : Exception("Failed to get changelog", cause)
class GetLatestAddedException(cause: Throwable) : Exception("Failed to last added mods", cause)
class GetLatestUpdatedException(cause: Throwable) : Exception("Failed to last updated mods", cause)
class GetTrendingException(cause: Throwable) : Exception("Failed to get trending mods", cause)
class GetModInfoException(cause: Throwable) : Exception("Failed to get mod info", cause)
class EndorseGiveException(cause: Throwable) : Exception("Failed to endorse mod", cause)
class EndorseTakeException(cause: Throwable) : Exception("Failed to take mod endorse", cause)

// Games
class FetchRemoteGameListException(cause: Throwable) : Exception(cause)
class FetchLocalGameListException(cause: Throwable) : Exception(cause)
class BookmarkGameException(cause: Throwable) : Exception(cause)

// User
class ValidateApiKeyException(cause: Throwable) : Exception(cause)
class GetTrackedModsException(cause: Throwable) : Exception("Failed to get tracked mods", cause)
class TrackModException(cause: Throwable) : Exception("Failed to track mod", cause)
class UntrackModException(cause: Throwable) : Exception("Failed to untrack mod", cause)
