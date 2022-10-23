package com.sedsoftware.nxmods

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
