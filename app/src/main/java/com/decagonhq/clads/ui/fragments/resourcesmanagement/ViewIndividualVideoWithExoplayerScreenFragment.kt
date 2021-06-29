package com.decagonhq.clads.ui.fragments.resourcesmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.adapters.ViewIndividualVideoAdapter
import com.decagonhq.clads.databinding.FragmentViewIndividualVideoWithExoplayerScreenBinding
import com.decagonhq.clads.utils.DummyDataGenerator
import com.decagonhq.clads.utils.ViewIndividualVideoClickListner
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView

/**
 * If you've found an error or have a feedback in this code, please contact me at
 *  paulndibe92@gmail.com
 */
class ViewIndividualVideoWithExoplayerScreenFragment :
    Fragment(),
    Player.Listener,
    ViewIndividualVideoClickListner {

    private var _binding: FragmentViewIndividualVideoWithExoplayerScreenBinding? = null
    val binding get() = _binding!!

    // intercepting arguments arguments comming from resources fragment
    private val args: ViewIndividualVideoWithExoplayerScreenFragmentArgs by navArgs()

    //
    private lateinit var viewIndividualVideoWithExoplayerRecyclerView: RecyclerView
    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var exoplayerView: PlayerView? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var rootViewGroup: ViewGroup? = null
    private lateinit var exoplayerProgressBar: ProgressBar
    private var playbackPosition: Long = 0
    private lateinit var videoUrl: String

    // Instantiating the adapter class
    private val viewIndividualVideoWithExoplayerRecyclerviewAdapter =
        ViewIndividualVideoAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewIndividualVideoWithExoplayerScreenBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * the arguments from the resources fragment is a string video url
         * the video url is to be used by the exoplayer to loaded the particular video
         */
        videoUrl = args.videoUrl

        // Getting the reference of the views in the layout
        exoplayerView = binding.playerView
        exoplayerProgressBar = binding.fragmentViewIndividualVideoProgressBar
        rootViewGroup = binding.constraintLayout
    }

    // Initializing the Exoplayer and recyclerview in the on start lifecycle
    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    // Releasing the Exoplayer instance in the on pause lifecycle so as to free up system resources
    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    // Releasing the Exoplayer instance in the on stop lifecycle so as to free up system resources
    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    /**
     * Initializing the Exoplayer and recyclerview in the on resume lifecycle and
     * making the screen go edge to edge
     */
    override fun onResume() {
        super.onResume()
        if (simpleExoPlayer == null) {
            initializePlayer()
        }
    }

    /**
     * Building Exoplayer and attaching it to the Exoplayer UI component of the
     */
    @Suppress("DEPRECATION")
    private fun initializePlayer() {
        if (simpleExoPlayer == null) {
            val trackSelector = DefaultTrackSelector(requireContext())
            trackSelector.setParameters(
                trackSelector.buildUponParameters().setMaxVideoSizeSd()
            )
            simpleExoPlayer = SimpleExoPlayer.Builder(requireContext())
                .setTrackSelector(trackSelector)
                .build()
            simpleExoPlayer!!.addListener(this as Player.EventListener)
            simpleExoPlayer!!.prepare()
        }

        exoplayerView!!.player = simpleExoPlayer

        // building the data source with video url
        val mediaItem = MediaItem.fromUri(videoUrl)
        val secondMediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))

        simpleExoPlayer!!.setMediaItem(mediaItem)
        simpleExoPlayer!!.addMediaItem(secondMediaItem)
        simpleExoPlayer?.playWhenReady = playWhenReady
        simpleExoPlayer?.seekTo(currentWindow, playbackPosition)
        simpleExoPlayer?.prepare()

        // initializing recyclerview
        var layoutManager = GridLayoutManager(requireContext(), 2)
        viewIndividualVideoWithExoplayerRecyclerView =
            binding.fragmentViewIndividualVideoWithExoplayerScreenRecyclerView
        viewIndividualVideoWithExoplayerRecyclerView.layoutManager = layoutManager
        viewIndividualVideoWithExoplayerRecyclerviewAdapter.setVideoResources(DummyDataGenerator.videoRecources)
        viewIndividualVideoWithExoplayerRecyclerView.adapter =
            viewIndividualVideoWithExoplayerRecyclerviewAdapter
    }

    /**
     * Releasing the Exoplayer to free system resources when the exoplayer is not need
     * but before freeing the exoplayer, the current playing video state is saved before releasing the
     * exoplayer, this will make exoplayer play the video from the previous state instead of starting
     * from beginning
     */
    private fun releasePlayer() {
        if (simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer!!.playWhenReady
            playbackPosition = simpleExoPlayer!!.currentPosition
            currentWindow = simpleExoPlayer!!.currentWindowIndex
            simpleExoPlayer!!.removeListener(this)
            simpleExoPlayer?.release()
        }
    }

    /**
     * Displaying a progress bar when the playback state is Buffering
     * and removing it when the video starts to play
     */
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            exoplayerProgressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            exoplayerProgressBar.visibility = View.INVISIBLE
    }

    /**
     * When a video item from the recyclerview is click, that video is loaded to
     * the exoplayer and the exoplayer plays the video at that position
     */
    override fun onItemClicked(videoUrl: String) {
        var mediaItem = MediaItem.fromUri(videoUrl)
        simpleExoPlayer?.setMediaItem(mediaItem)
        simpleExoPlayer?.playWhenReady = true
        simpleExoPlayer?.play()
    }

    // setting the _binding class to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
