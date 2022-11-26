package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.models.GameInfoModel
import com.sedsoftware.nxmods.network.stubs.Utils

internal object GetGame {

    val response: GameInfoModel
        get() = Utils.decodeFromJson(json)

    private val json = """
        {
            "id": 3333,
            "name": "Cyberpunk 2077",
            "forum_url": "https://forums.nexusmods.com/index.php?/forum/5744-cyberpunk-2077/",
            "nexusmods_url": "https://nexusmods.com/cyberpunk2077",
            "genre": "Action",
            "file_count": 33995,
            "downloads": 78471125,
            "domain_name": "cyberpunk2077",
            "approved_date": 1607433331,
            "file_views": 38945506,
            "authors": 2128,
            "file_endorsements": 1416209,
            "mods": 4756,
            "categories": [
                {
                    "category_id": 1,
                    "name": "Cyberpunk 2077",
                    "parent_category": false
                },
                {
                    "category_id": 2,
                    "name": "Miscellaneous",
                    "parent_category": 1
                },
                {
                    "category_id": 3,
                    "name": "Armour and Clothing",
                    "parent_category": 1
                },
                {
                    "category_id": 4,
                    "name": "Audio",
                    "parent_category": 1
                },
                {
                    "category_id": 5,
                    "name": "Characters",
                    "parent_category": 1
                },
                {
                    "category_id": 6,
                    "name": "Crafting",
                    "parent_category": 1
                },
                {
                    "category_id": 7,
                    "name": "Gameplay",
                    "parent_category": 1
                },
                {
                    "category_id": 8,
                    "name": "User Interface",
                    "parent_category": 1
                },
                {
                    "category_id": 9,
                    "name": "Utilities",
                    "parent_category": 1
                },
                {
                    "category_id": 10,
                    "name": "Visuals and Graphics",
                    "parent_category": 1
                },
                {
                    "category_id": 11,
                    "name": "Weapons",
                    "parent_category": 1
                },
                {
                    "category_id": 12,
                    "name": "Modders Resources",
                    "parent_category": 1
                },
                {
                    "category_id": 13,
                    "name": "Appearance",
                    "parent_category": 1
                },
                {
                    "category_id": 14,
                    "name": "Vehicles",
                    "parent_category": 1
                },
                {
                    "category_id": 15,
                    "name": "Animations",
                    "parent_category": 1
                },
                {
                    "category_id": 16,
                    "name": "Locations",
                    "parent_category": 1
                },
                {
                    "category_id": 17,
                    "name": "Scripts",
                    "parent_category": 1
                }
            ]
        }
    """.trimIndent()
}
