package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.models.GameInfoModel
import com.sedsoftware.nxmods.network.stubs.Utils

internal object GetGames {

    val response: List<GameInfoModel>
        get() = Utils.decodeFromJson(json)

    private val json = """
    [
       {
          "id":100,
          "name":"Morrowind",
          "forum_url":"https://forums.nexusmods.com/index.php?/forum/111-morrowind/",
          "nexusmods_url":"https://nexusmods.com/morrowind",
          "genre":"RPG",
          "file_count":32080,
          "downloads":43060976,
          "domain_name":"morrowind",
          "approved_date":1,
          "file_views":132972432,
          "authors":3042,
          "file_endorsements":1339569,
          "mods":9792,
          "categories":[
             {
                "category_id":1,
                "name":"Morrowind",
                "parent_category":false
             },
             {
                "category_id":2,
                "name":"Buildings",
                "parent_category":1
             },
             {
                "category_id":3,
                "name":"Dungeons and Locations",
                "parent_category":1
             },
             {
                "category_id":4,
                "name":"Gameplay",
                "parent_category":1
             },
             {
                "category_id":5,
                "name":"Guilds/Factions",
                "parent_category":1
             },
             {
                "category_id":6,
                "name":"Body, Face, and Hair",
                "parent_category":1
             },
             {
                "category_id":7,
                "name":"Items, Objects, and Clothes",
                "parent_category":1
             },
             {
                "category_id":8,
                "name":"Miscellaneous",
                "parent_category":1
             },
             {
                "category_id":9,
                "name":"Models and Textures",
                "parent_category":1
             },
             {
                "category_id":10,
                "name":"New Lands",
                "parent_category":1
             },
             {
                "category_id":11,
                "name":"NPCs",
                "parent_category":1
             },
             {
                "category_id":12,
                "name":"Official Plugins",
                "parent_category":1
             },
             {
                "category_id":13,
                "name":"Quests and Adventures",
                "parent_category":1
             },
             {
                "category_id":14,
                "name":"Races, Classes, and Birthsigns",
                "parent_category":1
             },
             {
                "category_id":15,
                "name":"Utilities",
                "parent_category":1
             },
             {
                "category_id":16,
                "name":"Weapons and Armour",
                "parent_category":1
             },
             {
                "category_id":17,
                "name":"Modders Resources and Tutorials",
                "parent_category":1
             },
             {
                "category_id":18,
                "name":"Bug Fixes",
                "parent_category":1
             },
             {
                "category_id":19,
                "name":"Creatures",
                "parent_category":1
             },
             {
                "category_id":20,
                "name":"Books and Scrolls",
                "parent_category":1
             },
             {
                "category_id":21,
                "name":"Animations",
                "parent_category":1
             },
             {
                "category_id":22,
                "name":"Cheats and God items",
                "parent_category":1
             },
             {
                "category_id":23,
                "name":"Companions",
                "parent_category":1
             },
             {
                "category_id":24,
                "name":"User Interface",
                "parent_category":1
             },
             {
                "category_id":25,
                "name":"Audio",
                "parent_category":1
             },
             {
                "category_id":26,
                "name":"ENB Preset",
                "parent_category":1
             },
             {
                "category_id":27,
                "name":"Player homes",
                "parent_category":1
             },
             {
                "category_id":28,
                "name":"Patches",
                "parent_category":1
             },
             {
                "category_id":29,
                "name":"Overhauls",
                "parent_category":1
             },
             {
                "category_id":30,
                "name":"Splash Screens",
                "parent_category":1
             },
             {
                "category_id":31,
                "name":"Weapons",
                "parent_category":1
             },
             {
                "category_id":33,
                "name":"Magic",
                "parent_category":1
             },
             {
                "category_id":34,
                "name":"Towns and Villages",
                "parent_category":1
             },
             {
                "category_id":35,
                "name":"Skills and Attributes",
                "parent_category":1
             },
             {
                "category_id":36,
                "name":"Immersion",
                "parent_category":1
             },
             {
                "category_id":37,
                "name":"Armour - Shields",
                "parent_category":1
             },
             {
                "category_id":38,
                "name":"Armour",
                "parent_category":1
             }
          ]
       },
       {
          "id":101,
          "name":"Oblivion",
          "forum_url":"https://forums.nexusmods.com/index.php?/forum/131-oblivion/",
          "nexusmods_url":"https://nexusmods.com/oblivion",
          "genre":"RPG",
          "file_count":62655,
          "downloads":238095312,
          "domain_name":"oblivion",
          "approved_date":1,
          "file_views":934821301,
          "authors":11398,
          "file_endorsements":5090587,
          "mods":30706,
          "categories":[
             {
                "category_id":20,
                "name":"Oblivion",
                "parent_category":false
             },
             {
                "category_id":22,
                "name":"New structures -  Buildings",
                "parent_category":20
             },
             {
                "category_id":24,
                "name":"Gameplay Effects and Changes",
                "parent_category":20
             },
             {
                "category_id":25,
                "name":"Guilds/Factions",
                "parent_category":20
             },
             {
                "category_id":26,
                "name":"Body, Face, and Hair",
                "parent_category":20
             },
             {
                "category_id":27,
                "name":"Items and Objects - Player",
                "parent_category":20
             },
             {
                "category_id":28,
                "name":"Miscellaneous",
                "parent_category":20
             },
             {
                "category_id":29,
                "name":"Models And Textures",
                "parent_category":20
             },
             {
                "category_id":30,
                "name":"New Lands",
                "parent_category":20
             },
             {
                "category_id":33,
                "name":"NPC",
                "parent_category":20
             },
             {
                "category_id":34,
                "name":"Races, Classes and Birthsigns",
                "parent_category":20
             },
             {
                "category_id":35,
                "name":"Quests And Adventures",
                "parent_category":20
             },
             {
                "category_id":36,
                "name":"Weapons And Armour",
                "parent_category":20
             },
             {
                "category_id":38,
                "name":"Official Plugins",
                "parent_category":20
             },
             {
                "category_id":39,
                "name":"Utilities",
                "parent_category":20
             },
             {
                "category_id":40,
                "name":"Cheats and God Items",
                "parent_category":20
             },
             {
                "category_id":41,
                "name":"Companions - CM Based",
                "parent_category":20
             },
             {
                "category_id":42,
                "name":"User Interfaces",
                "parent_category":20
             },
             {
                "category_id":43,
                "name":"Saved Games",
                "parent_category":20
             },
             {
                "category_id":45,
                "name":"Videos and Trailers",
                "parent_category":20
             },
             {
                "category_id":51,
                "name":"Animation",
                "parent_category":20
             },
             {
                "category_id":52,
                "name":"Millionth Member Competition",
                "parent_category":20
             },
             {
                "category_id":53,
                "name":"Cities, Towns, Villages and Hamlets",
                "parent_category":20
             },
             {
                "category_id":54,
                "name":"Armour",
                "parent_category":20
             },
             {
                "category_id":55,
                "name":"Weapons",
                "parent_category":20
             },
             {
                "category_id":58,
                "name":"Landscape Changes",
                "parent_category":20
             },
             {
                "category_id":60,
                "name":"Clothing",
                "parent_category":20
             },
             {
                "category_id":61,
                "name":"Audio, Sound and Music",
                "parent_category":20
             },
             {
                "category_id":62,
                "name":"Visuals and Graphics",
                "parent_category":20
             },
             {
                "category_id":65,
                "name":"Companions - Creatures",
                "parent_category":20
             },
             {
                "category_id":66,
                "name":"Companions - Other",
                "parent_category":20
             },
             {
                "category_id":67,
                "name":"Player homes",
                "parent_category":20
             },
             {
                "category_id":68,
                "name":"Castles, Palaces, Mansions and Estates",
                "parent_category":20
             },
             {
                "category_id":69,
                "name":"Mercantiles (shops, stores, inns, taverns, etc)",
                "parent_category":20
             },
             {
                "category_id":70,
                "name":"Ruins, Forts and abandoned structures",
                "parent_category":20
             },
             {
                "category_id":73,
                "name":"Skills and Leveling",
                "parent_category":20
             },
             {
                "category_id":74,
                "name":"Environmental",
                "parent_category":20
             },
             {
                "category_id":75,
                "name":"Magic - Spells \u0026 Enchantments",
                "parent_category":20
             },
             {
                "category_id":76,
                "name":"Stealth",
                "parent_category":20
             },
             {
                "category_id":77,
                "name":"Combat",
                "parent_category":20
             },
             {
                "category_id":78,
                "name":"Immersion",
                "parent_category":20
             },
             {
                "category_id":79,
                "name":"Overhauls",
                "parent_category":20
             },
             {
                "category_id":82,
                "name":"Modders Resources \u0026 Tutorials",
                "parent_category":20
             },
             {
                "category_id":83,
                "name":"Animals, Creatures, Mounts  \u0026 Horses",
                "parent_category":20
             },
             {
                "category_id":84,
                "name":"Patches",
                "parent_category":20
             },
             {
                "category_id":85,
                "name":"Items and Objects - World",
                "parent_category":20
             },
             {
                "category_id":88,
                "name":"Dungeons - New",
                "parent_category":20
             },
             {
                "category_id":89,
                "name":"Locations -  New",
                "parent_category":20
             },
             {
                "category_id":90,
                "name":"Locations - Vanilla",
                "parent_category":20
             },
             {
                "category_id":91,
                "name":"Dungeons - Vanilla",
                "parent_category":20
             },
             {
                "category_id":92,
                "name":"Collectables, Treasure Hunts and Puzzles",
                "parent_category":20
             },
             {
                "category_id":93,
                "name":"Magic - Gameplay",
                "parent_category":20
             },
             {
                "category_id":94,
                "name":"Magic - Alchemy",
                "parent_category":20
             },
             {
                "category_id":95,
                "name":"Oblivion 2.5 Millionth Member Competition",
                "parent_category":false
             },
             {
                "category_id":96,
                "name":"Competition",
                "parent_category":95
             },
             {
                "category_id":97,
                "name":"ENB Preset",
                "parent_category":20
             },
             {
                "category_id":98,
                "name":"Books and Scrolls",
                "parent_category":20
             }
          ]
       },
          {
             "id":120,
             "name":"Fallout 3",
             "forum_url":"https://forums.nexusmods.com/index.php?/forum/272-fallout-3/",
             "nexusmods_url":"https://nexusmods.com/fallout3",
             "genre":"ARPG",
             "file_count":38673,
             "downloads":143973932,
             "domain_name":"fallout3",
             "approved_date":1,
             "file_views":610539741,
             "authors":7451,
             "file_endorsements":4003423,
             "mods":15882,
             "categories":[
                {
                   "category_id":55,
                   "name":"Fallout 3",
                   "parent_category":false
                },
                {
                   "category_id":56,
                   "name":"Animation",
                   "parent_category":55
                },
                {
                   "category_id":58,
                   "name":"Cheats and God Items",
                   "parent_category":55
                },
                {
                   "category_id":59,
                   "name":"Utilities",
                   "parent_category":55
                },
                {
                   "category_id":60,
                   "name":"Videos and Trailers",
                   "parent_category":55
                },
                {
                   "category_id":61,
                   "name":"Save Games",
                   "parent_category":55
                },
                {
                   "category_id":62,
                   "name":"Hair and Face Models",
                   "parent_category":55
                },
                {
                   "category_id":63,
                   "name":"User Interface",
                   "parent_category":55
                },
                {
                   "category_id":64,
                   "name":"Miscellaneous",
                   "parent_category":55
                },
                {
                   "category_id":66,
                   "name":"Models and Textures",
                   "parent_category":55
                },
                {
                   "category_id":67,
                   "name":"Clothing",
                   "parent_category":55
                },
                {
                   "category_id":68,
                   "name":"Weapons",
                   "parent_category":55
                },
                {
                   "category_id":69,
                   "name":"Armour",
                   "parent_category":55
                },
                {
                   "category_id":70,
                   "name":"Sounds and Music",
                   "parent_category":55
                },
                {
                   "category_id":71,
                   "name":"Gameplay Effects and Changes",
                   "parent_category":55
                },
                {
                   "category_id":72,
                   "name":"Buildings",
                   "parent_category":55
                },
                {
                   "category_id":73,
                   "name":"Companions",
                   "parent_category":55
                },
                {
                   "category_id":74,
                   "name":"Guilds/Factions",
                   "parent_category":55
                },
                {
                   "category_id":75,
                   "name":"New Lands and Locations",
                   "parent_category":55
                },
                {
                   "category_id":76,
                   "name":"NPC",
                   "parent_category":55
                },
                {
                   "category_id":77,
                   "name":"Quests and Adventures",
                   "parent_category":55
                },
                {
                   "category_id":78,
                   "name":"Official",
                   "parent_category":55
                },
                {
                   "category_id":79,
                   "name":"Millionth Member Competition",
                   "parent_category":55
                },
                {
                   "category_id":88,
                   "name":"Radio stations",
                   "parent_category":55
                },
                {
                   "category_id":90,
                   "name":"Poses",
                   "parent_category":55
                },
                {
                   "category_id":101,
                   "name":"Bug Fixes",
                   "parent_category":55
                },
                {
                   "category_id":102,
                   "name":"Modders Resources and Tutorials",
                   "parent_category":55
                },
                {
                   "category_id":103,
                   "name":"ENB Preset",
                   "parent_category":55
                },
                {
                   "category_id":104,
                   "name":"Environment",
                   "parent_category":55
                },
                {
                   "category_id":105,
                   "name":"Visuals and Graphics",
                   "parent_category":55
                },
                {
                   "category_id":106,
                   "name":"Overhauls",
                   "parent_category":55
                },
                {
                   "category_id":107,
                   "name":"Player Homes",
                   "parent_category":55
                },
                {
                   "category_id":108,
                   "name":"Collectibles",
                   "parent_category":55
                },
                {
                   "category_id":109,
                   "name":"ReShade Preset",
                   "parent_category":55
                },
                {
                   "category_id":110,
                   "name":"Vehicles",
                   "parent_category":55
                },
                {
                   "category_id":111,
                   "name":"NPC - Vendors",
                   "parent_category":55
                },
                {
                   "category_id":112,
                   "name":"Weapons and Armour",
                   "parent_category":55
                },
                {
                   "category_id":113,
                   "name":"Ammo",
                   "parent_category":55
                },
                {
                   "category_id":114,
                   "name":"Creatures",
                   "parent_category":55
                },
                {
                   "category_id":115,
                   "name":"Patches",
                   "parent_category":55
                },
                {
                   "category_id":116,
                   "name":"Performance",
                   "parent_category":55
                },
                {
                   "category_id":117,
                   "name":"Perks",
                   "parent_category":55
                }
             ]
          }
    ]
    """.trimIndent()
}
