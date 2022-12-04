package com.sedsoftware.nxmods.root

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
