package com.chipmunksmedia.helldivers.remote.ui.components.media

import android.view.SurfaceView
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

@Composable
internal actual fun VideoPlayerImpl(
    url: String,
    isResumed: Boolean,
    volume: Float,
    speed: Float,
    seek: Float,
    isFullscreen: Boolean,
    isBuffering: MutableState<Boolean>,
    progressState: MutableState<Progress>,
    modifier: Modifier,
    onFinish: (() -> Unit)?,
) {
    val context = LocalContext.current

    val player: Player = remember { ExoPlayer.Builder(context).build() }
    val videoView = remember { SurfaceView(context) }

    val mediaItem = remember(url) { MediaItem.Builder().setMediaId(url).setUri(url).build() }
    LaunchedEffect(mediaItem, player) {
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    // Update mutable states
    player.emitProgressTo(progressState)
    LaunchedEffect(player.playbackState) {
        isBuffering.value = player.playbackState == Player.STATE_BUFFERING
    }

    // Update player
    LaunchedEffect(seek) { player.seekTo((seek * player.duration).toLong()) }
    LaunchedEffect(speed) { player.playbackParameters = player.playbackParameters.withSpeed(speed) }
    LaunchedEffect(volume) { player.volume = volume }
    LaunchedEffect(isResumed) { if (!isResumed) player.pause() else player.play() }

    Surface(modifier = modifier) {
        AndroidView(
            factory = { videoView },
            update = { player.setVideoSurfaceView(it) },
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            player.clearVideoSurface()
            player.release()
        }
    }
}
