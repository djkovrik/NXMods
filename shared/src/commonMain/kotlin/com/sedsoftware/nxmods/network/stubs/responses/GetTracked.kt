package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.models.TrackingInfoModel
import com.sedsoftware.nxmods.network.stubs.Utils

internal object GetTracked {

    val response: List<TrackingInfoModel>
        get() = Utils.decodeFromJson(json)

    private val json = """
        [
            {
                "mod_id": 1,
                "domain_name": "pillarsofeternity"
            },
            {
                "mod_id": 1,
                "domain_name": "xcom2"
            },
            {
                "mod_id": 1,
                "domain_name": "greedfall"
            },
            {
                "mod_id": 4,
                "domain_name": "greedfall"
            },
            {
                "mod_id": 5,
                "domain_name": "witcher2"
            },
            {
                "mod_id": 5,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 5,
                "domain_name": "pathfinderkingmaker"
            },
            {
                "mod_id": 6,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 7,
                "domain_name": "witcher2"
            },
            {
                "mod_id": 7,
                "domain_name": "pathfinderkingmaker"
            },
            {
                "mod_id": 8,
                "domain_name": "greedfall"
            },
            {
                "mod_id": 12,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 15,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 18,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 19,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 22,
                "domain_name": "greedfall"
            },
            {
                "mod_id": 23,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 23,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 24,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 26,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 30,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 35,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 41,
                "domain_name": "greedfall"
            },
            {
                "mod_id": 47,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 48,
                "domain_name": "pillarsofeternity"
            },
            {
                "mod_id": 50,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 57,
                "domain_name": "xcom2"
            },
            {
                "mod_id": 62,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 66,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 67,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 70,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 77,
                "domain_name": "cyberpunk2077"
            },
            {
                "mod_id": 80,
                "domain_name": "masseffect"
            },
            {
                "mod_id": 81,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 82,
                "domain_name": "xcom2"
            },
            {
                "mod_id": 82,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 85,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 85,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 87,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 97,
                "domain_name": "witcher2"
            },
            {
                "mod_id": 103,
                "domain_name": "pathfinderkingmaker"
            },
            {
                "mod_id": 104,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 105,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 106,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 107,
                "domain_name": "cyberpunk2077"
            },
            {
                "mod_id": 111,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 113,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 116,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 116,
                "domain_name": "witcher2"
            },
            {
                "mod_id": 122,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 129,
                "domain_name": "pillarsofeternity2"
            },
            {
                "mod_id": 132,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 143,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 145,
                "domain_name": "divinityoriginalsin2"
            },
            {
                "mod_id": 149,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 161,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 164,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 164,
                "domain_name": "cyberpunk2077"
            },
            {
                "mod_id": 165,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 165,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 183,
                "domain_name": "fallout3"
            },
            {
                "mod_id": 185,
                "domain_name": "cyberpunk2077"
            },
            {
                "mod_id": 190,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 192,
                "domain_name": "witcher3"
            },
            {
                "mod_id": 193,
                "domain_name": "skyrim"
            },
            {
                "mod_id": 197,
                "domain_name": "fallout3"
            }
        ]
    """.trimIndent()
}
