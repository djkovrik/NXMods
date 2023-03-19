package com.sedsoftware.nxmods.utils

import kotlin.test.Test
import kotlin.test.assertTrue

class BBCodeConverterTest {

    @Test
    fun converterTest() {
        val converter = BBCodeConverter()
        val converted = converter.convertToMarkdown(TEXT)
        assertTrue { converted.isNotEmpty() }
    }

    companion object {
        private const val TEXT = "[b][u][color=#00ff00][size=4][img]https://media.giphy.com/media/aNetD4ZmOOJTnJpaki/giphy.gif[/img]\n" +
            "<br />\n<br />What it does:[/size][/color][/u][/b]\n<br />- Lets you sit down almost everywhere in Night City to soak in its " +
            "atmosphere\n<br />- Includes Benches, Chairs, Couches and everything that resembles those\n<br />- Not included are bar " +
            "chairs\n<br />\n<br />[b][u][color=#00ffff][size=4]Installation:\n<br />[/size][/color][/u][/b]- Download and install " +
            "[url=https://www.nexusmods.com/cyberpunk2077/mods/107?tab=description]CET[/url] (Latest version)\n<br />- Download and " +
            "install the [url=https://www.nexusmods.com/cyberpunk2077/mods/3518]Native Settings UI[/url]\uFEFF mod (Optional)\n<br />" +
            "- Download the mod and extract it into your game folder (The one containing /bin, /r6 etc...)\n<br />\n<br />[color=#00ff00]" +
            "[size=4][b][u]How to use:\n<br />[/u][/b][/size][/color]- For the scanning to work you can not: Be wanted, In Combat, In a " +
            "restricted area or in a cutscene or similar\n<br />- Walk up to the Bench/Chair/Couch (Or anything that resembles the general " +
            "shape) and hold down your \\\"Scan\\\" (Tab) key, while looking at the sittable surface\n<br />- Once the sitting popup shows, " +
            "you can sit down\n<br />- For certain objects, like the [url=https://postimg.cc/mPdGg5s6]low metal benches[/url], you may need " +
            "to stand a bit further away for it to get detected\n<br />- You can optionally choose to hide the \\\"Stand\\\" popup in the " +
            "settings, and only show it while holding the \\\"Scan\\\" (Tab) key\n<br />\n<br />[b][i][color=#ff00ff]Thanks to psiberx for " +
            "GameUI.lua and Cron.lua, and scornthegreat for helping with testing\n<br />\n<br />[b][u][color=#00ff00][size=4][b][u]" +
            "[color=#00ff00][size=4][i][url=https://www.buymeacoffee.com/keanuWheeze][img]https://cdn.jsdelivr.net/gh/" +
            "justarandomguyintheinternet/keanuWheeze@main/buymeacoffee.png[/img][/url][/i][/size][/color][/u][/b][/size][/color][/u][/b]" +
            "[/color][/i][/b]"
    }
}
