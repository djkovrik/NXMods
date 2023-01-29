package com.sedsoftware.nxmods.domain.tools

interface NxModsSettings {
    var name: String
    var avatar: String
    var isPremium: Boolean
    var isSupporter: Boolean
    var apiKey: String
    var isProfileValidated: Boolean
    var currentGameName: String
    var currentGameDomain: String
}
