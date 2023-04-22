import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.NxModsRootFactory
import com.sedsoftware.nxmods.ui.NxModsRootContent
import com.sedsoftware.nxmods.ui.theme.NxModsTheme
import com.sedsoftware.nxmods.utils.ImageLoaderFactory
import com.seiko.imageloader.LocalImageLoader

private val lifecycle = LifecycleRegistry()

@Suppress("FunctionName")
fun MainViewController() = ComposeUIViewController {

    val root: NxModsRoot = NxModsRootFactory(DefaultComponentContext(lifecycle))

    NxModsTheme {
        CompositionLocalProvider(
            LocalImageLoader provides ImageLoaderFactory(),
        ) {
            NxModsRootContent(root)
        }
    }
}
