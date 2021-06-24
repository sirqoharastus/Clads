package com.decagonhq.clads.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

fun createGridLayoutManager(context: Context): RecyclerView.LayoutManager{
    return GridLayoutManager(context, 2)
}
fun createExoPlayer(context: Context): SimpleExoPlayer{
    val simpleExoPlayer = SimpleExoPlayer.Builder(context)
    val trackSelector = DefaultTrackSelector(context)
    trackSelector.setParameters(
        trackSelector.buildUponParameters().setMaxVideoSizeSd()
    )
    return simpleExoPlayer
        .setTrackSelector(trackSelector)
        .build()
}