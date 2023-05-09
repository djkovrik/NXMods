package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.models.ModInfoModel
import com.sedsoftware.nxmods.network.stubs.Utils

internal object GetMod {

    val response: ModInfoModel
        get() = Utils.decodeFromJson(json)

    private val json = """
        {
            "name": "RED4ext",
            "summary": "A script extender for REDengine 4.",
            "description": "RED4ext is a library that extends REDengine 4. It will allow modders to add new features, modify the game behavior, add new scripting functions or call existing ones in your own plugins.\n<br />\n<br />This library is similar to:\n<br />[list]\n<br />[*][url=http://dev-c.com/GTAV/scripthookv]Script Hook V[/url]\n<br />[*][url=https://skse.silverlock.org/]Skyrim Script Extender[/url]\n<br />[/list]\n<br />This library is split into two parts:\n<br />[list]\n<br />[*][url=https://github.com/WopsS/RED4ext]RED4ext[/url] - This project which consist of the loader, it only takes care of managing plugins.\n<br />[*][url=https://github.com/WopsS/RED4ext.SDK]RED4ext.SDK[/url] - This project contains the reversed types and helpers to extend the engine, it can be used independently of RED4ext.\n<br />[/list]\n<br />If you are developing a new plugin, add only [url=https://github.com/WopsS/RED4ext.SDK]RED4ext.SDK[/url] in your project structure.\n<br />\n<br />[size=4]Installing[/size]\n<br />\n<br />[list=1]\n<br />[*][b][/b][b][/b][b]Download [/b]the latest zip (e.g. [b]red4ext_x.y.z.zip[/b]) from [url=https://github.com/WopsS/RED4ext/releases/latest]GitHub [/url]﻿or [url=https://www.nexusmods.com/cyberpunk2077/mods/2380]NexusMods[/url]﻿.\n<br />[*][b]Extract [/b]the content of the archive in the game's directory.\n<br />[*][b]Launch [/b]the game.\n<br />[*]([b]Optional[/b]) Check the log file in [b]&lt;game_directory&gt;/red4ext/logs/red4ext.log[/b] to make sure everything works.\n<br />[/list]\n<br />[size=4]Uninstall[/size]\n<br />\n<br />[list=1]\n<br />[*]Remove [b]d3d11.dll[/b] / [b]powrprof.dll[/b] from [b]&lt;game_directory&gt;/bin/x64[/b].\n<br />[*]([b]Optionally[/b]) Remove [b]red4ext [/b]directory from [b]&lt;game_directory&gt;[/b].\n<br />[/list]\n<br />The complete / most up to date documentation is now available at [url=https://docs.red4ext.com]docs.red4ext.com[/url].",
            "picture_url": "https://staticdelivery.nexusmods.com/mods/3333/images/2380/2380-1618829826-1376548256.png",
            "mod_downloads": 892560,
            "mod_unique_downloads": 519748,
            "uid": 14315125999948,
            "mod_id": 2380,
            "game_id": 3333,
            "allow_rating": true,
            "domain_name": "cyberpunk2077",
            "category_id": 12,
            "version": "1.9.0",
            "endorsement_count": 13016,
            "created_timestamp": 1618832883,
            "created_time": "2021-04-19T11:48:03.000+00:00",
            "updated_timestamp": 1667929438,
            "updated_time": "2022-11-08T17:43:58.000+00:00",
            "author": "WopsS",
            "uploaded_by": "WopsS",
            "uploaded_users_profile_url": "https://nexusmods.com/users/16475274",
            "contains_adult_content": false,
            "status": "published",
            "available": true,
            "user": {
                "member_id": 16475274,
                "member_group_id": 3,
                "name": "WopsS"
            },
            "endorsement": {
                "endorse_status": "Endorsed",
                "timestamp": 1618930341,
                "version": null
            }
        }
    """.trimIndent()
}
