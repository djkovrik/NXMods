package com.sedsoftware.nxmods.domain.exception

// Mods
class LoadModsListException(cause: Throwable) : Exception(cause)


// Games
class FetchRemoteGameListException(cause: Throwable) : Exception(cause)
class FetchLocalGameListException(cause: Throwable) : Exception(cause)
class BookmarkGameException(cause: Throwable) : Exception(cause)

// User
class ValidateApiKeyException(cause: Throwable) : Exception(cause)
