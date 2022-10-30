package com.sedsoftware.nxmods.domain.tools

interface NxModsSettings {
    var name: String
    var avatar: String
    var isPremium: Boolean
    var isSupporter: Boolean
    var apiKey: String
    var isProfileValid: Boolean
    var currentDomain: String
}
