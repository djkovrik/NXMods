package com.sedsoftware.nxmods.network

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority
import okio.Path.Companion.toOkioPath
import java.io.File

@Suppress("FunctionName", "MagicNumber")
fun ImageLoaderFactory(): ImageLoader {
    return ImageLoader {
        logger = DebugLogger(LogPriority.VERBOSE)

        components {
            setupDefaultComponents(imageScope)
        }
        interceptor {
            memoryCacheConfig {
                maxSizePercent(0.25)
            }
            diskCacheConfig {
                directory(getCacheDir().resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024)
            }
        }
    }
}

enum class OperatingSystem {
    Windows, Linux, MacOS, Unknown,
}

private val currentOperatingSystem: OperatingSystem
    get() {
        val system = System.getProperty("os.name").lowercase()
        return if (system.contains("win")) {
            OperatingSystem.Windows
        } else if (system.contains("nix") || system.contains("nux") ||
            system.contains("aix")
        ) {
            OperatingSystem.Linux
        } else if (system.contains("mac")) {
            OperatingSystem.MacOS
        } else {
            OperatingSystem.Unknown
        }
    }

private fun getCacheDir() = when (currentOperatingSystem) {
    OperatingSystem.Windows -> File(System.getenv("AppData"), "NxMods/cache")
    OperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/NxMods")
    OperatingSystem.MacOS -> File(System.getProperty("user.home"), "Library/Caches/NxMods")
    else -> error("Unsupported operating system")
}
