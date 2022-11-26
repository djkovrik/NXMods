package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.stubs.Utils

internal object GetChangelog {

    val response: Map<String, List<String>>
        get() = Utils.decodeFromJson(json)

    private val json = """
        {
            "0.1.0": [
                "Initial release"
            ],
            "0.2.0": [
                "Fixed police stars widget appearance for both minimap positioning addons"
            ],
            "0.3.0": [
                "Added extended configuration options for most widget modules (check the mod description for details)",
                "Added new module: Player Healthbar"
            ],
            "0.4.0": [
                "Added new in-game hotkey for minimap toggle"
            ],
            "0.4.1": [
                "Preconfigured options.json removed from downloads to avoid possible conflicts with other mods, hotkey setup guide now contains instructions how to modify it manually."
            ],
            "0.4.2": [
                "Hotkey guide updated, removed unnecessary steps (if you already have the hotkey configured you do not need this update)"
            ],
            "0.5.0": [
                "Fixed vehicle summon widget position for both minimap positioning addons",
                "Minimap position addon removed from default All in One package",
                "Minimap hotkey guide included into related mod archives"
            ],
            "0.5.1": [
                "More tweaks for vehicle summon widget position for Center Right minimap addon, widget moved to bottom of the minimap"
            ],
            "0.6.0": [
                "Crouch Indicator and Weapon Roster: module logic optimized to honor default widget fold/unfold animations so visibility changes correctly with all animations now",
                "Crouch Indicator and Weapon Roster: fixed widget background visibility removal to properly hide that red diamond indicator in the bottom right corner"
            ],
            "0.7.0": [
                "All in One and Minimap modules now include basegame_hud_minimap_scanner_tweak.archive which disables default game behavior when minimap was forcefully hidden by scanner activation",
                "New configuration option for Minimap module: Scanner",
                "Minimap zoom addon removed from default All in One package and available as optional file only",
                "Minimap, Quest Tracker and World Markers modules logic optimized to work with activated braindance mode"
            ],
            "1.0.0": [
                "Added new Global Toggle hotkey which you can use to toggle visibility for any widget combination by your choice",
                "Added Hints module which controls keyboard hints widget visibility (Draw weapon etc.)",
                "Added Stealth visibility option for Crouch Indicator and Weapon Roster module",
                "Removed separate module archives, from now you can disable any module by using config file",
                "Updated included hotkey definitions guide"
            ],
            "1.0.1": [
                "Compatibility update to fix conflicts with Modular HUD and UI Tweaker mod"
            ],
            "1.1.0": [
                "Player Healthbar: added separate options for each of the default visibility conditions so you can enable or disable them as you like (HP not full, memory not full, buffs active, quickhacks active, combat mode active)",
                "Added an optional file which allows to scale dialog widget size",
                "Added an optional file which hides crosshair aiming dot when weapon is sheathed"
            ],
            "1.2.0": [
                "Added new module visibility condition: show when out of combat (please note that it does not include stealth as it has its own separate option)"
            ],
            "1.3.0": [
                "Fixed a bug when resulting visibility for Player Healthbar sometimes was applied incorrectly after the initial game loading",
                "Tweaked OutOfCombat visibility option, now when enabled it does not override Scanner, Vehicle, Weapon and Zoom options"
            ],
            "1.4.0": [
                "Compatibility update for the new redscript release which should reduce possible conflicts with other mods and future game patches. WARNING: minimal required redscript version from now on is 0.2.4",
                "A few script tweaks for compatibility with my next upcoming mod"
            ],
            "1.5.0": [
                "Scripts updated for patch 1.3 compatibility",
                "Minimap for scanner feature temporarily removed so please delete basegame_hud_minimap_scanner_tweak.archive file if you have it"
            ],
            "1.5.1": [
                "Fixed incorrect unsheathed weapon detection for crouch indicator"
            ],
            "1.6.0": [
                "Fixed an issue with player healthbar widget caused by patch 1.3 when sometimes you may have it stuck on screen if ShowWhenMemoryNotFull option was enabled",
                "Improved the mod hotkeys registration system",
                "Simple HUD toggle hotkey logic improved as well to prevent cases when it may not work",
                "Simple HUD toggle default hotkey changed from Z to F1"
            ],
            "1.6.1": [
                "Tweaked logic for OutOfCombat visibility option, now it applied correctly after the initial game loading",
                "Improved Minimap Zoom compatibility patch migrated to LHUD downloads"
            ],
            "1.7.0": [
                "Minimap for scanner option restored"
            ],
            "1.7.1": [
                "Fixed a bug when minimap was not visible during car races",
                "Optimized widgets positions to reduce quest tracker and scanner details overlap while using scanner in braindance mode"
            ],
            "2.0.0": [
                "The mod files structure changed, you MUST delete all files from the previous version (I've included simple bat-file which deletes all deprecated LHUD files)",
                "Core mod logic migrated to event based approach which gives more flexibility and simplifies future development",
                "Crouch indicator and weapon roster widgets now have separate config blocks",
                "ShowInCombat visibility condition now has higher priority compared to ShowWithWeapon",
                "Global world markers config replaced with five separate config blocks which control related world marker types: quest markers, loot, places of interest, combat and owned vehicles",
                "Additional config options for crouch indicator and weapon roster"
            ],
            "2.0.1": [
                "Fixed quest tracker visibility for braindance mode",
                "Fixed icons above vendor heads which were not controlled by the mod logic",
                "Added new config section where you can turn off device and distraction markers displaying for scanner mode",
                "Disabled LHUD debug logs displaying"
            ],
            "2.0.2": [
                "Fixed quest tracker position for braindance mode",
                "Fixed a bug with widgets disappearing after exit from braindance mode",
                "Fixed a bug with on screen widgets stuck if you mount a vehicle with unsheathed weapon"
            ],
            "2.0.3": [
                "Fixed missed weapon unsheathing event for radial wheel menu",
                "Improved grenade markers visibility logic"
            ],
            "2.0.4": [
                "Added additional option which controls if tracked place of interest marker must be displayed all the time (config file updated: added AlwaysShowTrackedMarker option for POI markers config)"
            ],
            "2.0.5": [
                "All core scripts updated for patch 1.5 compatibility (there were no changes in config file so you still can use the previous one)",
                "Global hotkey migrated from `G` to `Y` because `G` now used by the game itself (you have to update your configured xml files if you are already have IK_G key there)",
                "Updated misc modules: No Aim Dot Sheathed, No Enemy Highlight, No Marker Pulse, others are still compatible",
                "Fixed a bug when smartlink label get stuck on the screen no matter of weapon state",
                "Updated Improved Minimap Zoom patch"
            ],
            "2.0.6": [
                "Fixed a bug when initial markers state wasn't applied correctly after the game loading",
                "Fixed enemy tagging",
                "ShowWithWeapon option now does not depend on combat state",
                "Config file wasn't updated so you can keep the previous one"
            ],
            "2.0.7": [
                "IsEnabled option now correctly enables and disables related modules",
                "More tweaks for combat markers logic"
            ],
            "2.0.8": [
                "More improvements for combat and grenade markers visibility behavior",
                "Added optional addon for Native Settings UI support"
            ],
            "2.0.9": [
                "All optional addons integrated into the main mod with their own config section",
                "LHUD Addons config category added to Native Settings UI menu",
                "Minor fixes for player healthbar logic to prevent vanilla game bug with healthbar being stuck on screen",
                "Fixed a bug when grenade throw also triggered weapon sheathe event for all widgets"
            ],
            "2.1.0": [
                "Fixed a bug when using consumables (heals etc.) triggered weapon sheathe event for all widgets",
                "Fixed money transfers widget visibility",
                "Enemy Highlighting module restored and integrated into addons section",
                "Added compatibility patch for Spicy's E3 2018 HUD",
                "Minor improvements to muting quest notifications sound options"
            ],
            "2.1.1": [
                "More tweaks and improvements to \"Show with weapon\" option which should address major issues with incorrect equipped weapon state detection"
            ],
            "2.1.2": [
                "Fixed initial widgets state after loading a saved game where player mounted to vehicle",
                "Fixed initial widgets state after loading a saved game where player has weapon unsheathed"
            ],
            "2.1.3": [
                "Added new addon to control NPC highlighting color for Ricochet effect",
                "Added new addon to disable some input button prompts (Talk, Get In etc.)",
                "Native Settings UI section renamed from Limited HUD to LHUD"
            ],
            "2.1.4": [
                "Improved braindance handling for cases when you activate braindance mode sitting in vehicle",
                "Improved global hotkey flag state logic, now it behaves correctly after HUD refreshing when you control cameras"
            ],
            "2.2.0": [
                "New option for quest tracker widget: displaying for a short time when journal gets updates",
                "Fixed a bug when always show tracked option did not work for some kinds of markers",
                "Player stash marker now controlled by POI markers module",
                "Native Settings UI merged into the main archive",
                "Added localizations support"
            ],
            "2.2.1": [
                "Fixed a bug when owned vehicles settings were applied incorrectly sometimes",
                "Added French and Traditional Chinese localizations"
            ],
            "2.2.2": [
                "Rearranged LHUD Addons config menu, added new \"Widgets Remover\" section",
                "New option to disable overhead dialog subtitles for hostile NPCs",
                "Minor fixes to the mod localizations"
            ],
            "2.2.3": [
                "More tweaks for compatibility with Improved Minimap Zoom mod so additional patch is not needed anymore",
                "Minor tweaks to native settings menu logic",
                "Spanish localization added"
            ],
            "2.2.4": [
                "More improvements for disable overhead subtitles option, now it keeps subtitles for civilians and some friendly factions and removes for everyone else"
            ],
            "2.3.0": [
                "Manual hotkeys configuration replaced with Input Loader integration"
            ],
            "2.3.1": [
                "Added a new option to remove new area notifications"
            ],
            "2.3.2": [
                "Added basic 1.6 patch support but you can expect another update with migration to Mod Settings soon"
            ],
            "2.4.0": [
                "Migrated from Native Settings UI to Mod Settings",
                "Added build in ArchiveXL availability checker"
            ],
            "2.4.1": [
                "Fixed duplicated Show with zoom option in Action Buttons menu config section"
            ],
            "2.4.2": [
                "Fixed broken red color option for objects fill coloring settings, after installing this update just  open LHUD Addons settings menu and reset everything to defaults to apply the new fix"
            ],
            "2.4.3": [
                "Added Simplified Chinese translation",
                "Fix evolution icon option removed, use Power Tech Icon Fix mod instead"
            ],
            "2.4.4": [
                "Added Czech and Japanese translations"
            ],
            "2.5.0": [
                "Added current district name displaying to minimap widget because why not (if you are using Spicy's E3 2018 HUD then you might not need this so just find and delete hudDistrictName.reds)",
                "Simple HUD toggle hotkey now configurable with LimitedHUD.xml",
                "Fixed a few issues with minimap and tracked markers which sometimes appeared during braindance scenes"
            ],
            "2.5.1": [
                "Minor tweaks to current district label displaying during combat and for restricted and dangerous areas"
            ]
        }
    """.trimIndent()
}
