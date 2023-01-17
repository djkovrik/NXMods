@file:Suppress("MagicNumber")

package com.sedsoftware.nxmods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.nxmods.database.DatabaseFeatureComponent
import com.sedsoftware.nxmods.network.NetworkFeatureComponent
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.integration.NxModsRootComponent
import com.sedsoftware.nxmods.settings.SettingsFeatureComponent
import com.sedsoftware.nxmods.ui.NxModsRootContent

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {

    private val databaseFeature: DatabaseFeatureComponent
        get() = DatabaseScopedComponent.get(applicationContext)

    private val settingsFeature: SettingsFeatureComponent
        get() = SettingsScopedComponent.get(applicationContext)

    private val networkFeature: NetworkFeatureComponent
        get() = NetworkScopedComponent.get(settingsFeature)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root: NxModsRoot = todoRoot(defaultComponentContext())

        setContent {
            MyApplicationTheme {
                NxModsRootContent(root)
            }
        }
    }

    private fun todoRoot(componentContext: ComponentContext): NxModsRoot =
        NxModsRootComponent(
            componentContext = componentContext,
            storeFactory = DefaultStoreFactory(),
            nxModsApi = networkFeature.api,
            nxModsDatabase = databaseFeature.database,
            nxModsSettings = settingsFeature.settings
        )
}
