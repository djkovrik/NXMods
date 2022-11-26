package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.models.EndorsementInfoModel
import com.sedsoftware.nxmods.network.stubs.Utils

internal object GetEndorsed {

    val response: List<EndorsementInfoModel>
        get() = Utils.decodeFromJson(json)

    private val json = """
        [
            {
                "mod_id": 4901,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 6730,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 6855,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 6870,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 7228,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 8273,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 8587,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 8771,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 9562,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 9655,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 12588,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 15802,
                "domain_name": "oblivion",
                "date": "2009-07-06T18:34:48.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 16120,
                "domain_name": "oblivion",
                "date": "2009-07-06T14:12:48.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 22282,
                "domain_name": "oblivion",
                "date": "1970-01-01T00:00:00.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 22368,
                "domain_name": "oblivion",
                "date": "2011-03-19T20:09:25.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 26389,
                "domain_name": "oblivion",
                "date": "2009-09-07T04:41:03.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 19,
                "domain_name": "skyrim",
                "date": "2014-06-19T16:44:08.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 60,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:46:19.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 193,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:06.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 238,
                "domain_name": "skyrim",
                "date": "2011-11-19T08:57:48.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 263,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:46:02.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 601,
                "domain_name": "skyrim",
                "date": "2015-03-19T14:49:26.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 607,
                "domain_name": "skyrim",
                "date": "2011-12-11T18:54:17.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 1002,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:46:07.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 1037,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:43:59.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 1111,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:42:53.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 1334,
                "domain_name": "skyrim",
                "date": "2015-03-08T11:40:01.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 2121,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:45:32.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 2317,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:36:50.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 2636,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:09.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 2782,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:03.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 2875,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:43:56.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 2889,
                "domain_name": "skyrim",
                "date": "2014-06-20T08:57:19.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3018,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:43:51.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3145,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:46:30.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3222,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:26:36.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3397,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:52.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3621,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:39:12.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3693,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:43:55.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3829,
                "domain_name": "skyrim",
                "date": "2015-02-20T14:29:27.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 3863,
                "domain_name": "skyrim",
                "date": "2015-04-25T08:23:04.000Z",
                "version": null,
                "status": "Abstained"
            },
            {
                "mod_id": 4020,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:37:10.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 4817,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:25:33.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 4834,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:43:05.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 4955,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:42:03.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 5727,
                "domain_name": "skyrim",
                "date": "2015-03-14T17:23:10.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 5941,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:46.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 6387,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:41:03.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 6554,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:55.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 7305,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:44:28.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 7416,
                "domain_name": "skyrim",
                "date": "2015-03-19T14:49:04.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 7901,
                "domain_name": "skyrim",
                "date": "2015-03-19T14:50:39.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 8042,
                "domain_name": "skyrim",
                "date": "2015-04-14T15:45:14.000Z",
                "version": null,
                "status": "Endorsed"
            },
            {
                "mod_id": 6421,
                "domain_name": "cyberpunk2077",
                "date": "2022-11-19T19:48:56.000Z",
                "version": null,
                "status": "Endorsed"
            }
        ]
    """.trimIndent()
}
