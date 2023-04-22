package com.sedsoftware.nxmods.utils

import android.content.Context
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority
import okio.Path.Companion.toOkioPath

@Suppress("FunctionName", "MagicNumber")
fun ImageLoaderFactory(context: Context): ImageLoader {
    return ImageLoader {
        logger = DebugLogger(LogPriority.VERBOSE)

        components {
            setupDefaultComponents(context)
        }
        interceptor {
            memoryCacheConfig {
                maxSizePercent(context, 0.25)
            }
            diskCacheConfig {
                directory(context.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024)
            }
        }
    }
}
