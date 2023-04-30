import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.NxModsRootFactory
import com.sedsoftware.nxmods.ui.NxModsRootContent
import com.sedsoftware.nxmods.utils.ImageLoaderFactory
import com.seiko.imageloader.LocalImageLoader

val lifecycle = LifecycleRegistry()
val root: NxModsRoot = NxModsRootFactory(DefaultComponentContext(lifecycle))

fun MainViewController() = ComposeUIViewController {
    Surface(modifier = Modifier.fillMaxSize()) {
        MaterialTheme {
            CompositionLocalProvider(
                LocalImageLoader provides ImageLoaderFactory(),
            ) {
                NxModsRootContent(root)
            }
        }
    }
}
