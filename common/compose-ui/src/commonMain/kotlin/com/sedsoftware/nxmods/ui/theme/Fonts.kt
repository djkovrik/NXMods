package com.sedsoftware.nxmods.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.sedsoftware.nxmods.platform.font

object Fonts {
    @Composable
    fun lato() = FontFamily(
        font("Lato", "lato_regular", FontWeight.Normal, FontStyle.Normal),
        font("Lato", "lato_italic", FontWeight.Normal, FontStyle.Italic),
        font("Lato", "lato_bold", FontWeight.Bold, FontStyle.Normal),
        font("Lato", "lato_bold_italic", FontWeight.Bold, FontStyle.Italic),
        font("Lato Medium", "lato_medium", FontWeight.Medium, FontStyle.Normal),
        font("Lato Medium", "lato_medium_italic", FontWeight.Medium, FontStyle.Italic),
        font("Lato Light", "lato_light", FontWeight.Light, FontStyle.Normal),
        font("Lato Light", "lato_light_italic", FontWeight.Light, FontStyle.Italic),
        font("Lato Thin", "lato_thin", FontWeight.Thin, FontStyle.Normal),
        font("Lato Thin", "lato_thin_italic", FontWeight.Thin, FontStyle.Italic)
    )
}
