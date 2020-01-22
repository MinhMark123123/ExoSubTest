package m.age.myapplication

import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.SingleSampleMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_player_sub.*

class PlayerSubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_player_sub)
        val bandwidthMeter = DefaultBandwidthMeter.Builder(this).build()
        val player = SimpleExoPlayer.Builder(this).setBandwidthMeter(bandwidthMeter).build()
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, "yourApplicationName")
        )
        // This is the MediaSource representing the media to be played.
        // This is the MediaSource representing the media to be played.
        val videoSource: MediaSource = ProgressiveMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(
                Uri.parse("https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
            )
        val subtitle = SingleSampleMediaSource.Factory(dataSourceFactory).createMediaSource(
            Uri.parse("https://dramacute.com/subtitle/files/Doctor.Detective/01.srt"),
            Format.createTextSampleFormat(
                null,
                MimeTypes.APPLICATION_SUBRIP, // The mime type. Must be set correctly.
                Format.NO_VALUE,
                "en",
                null
            ),
            C.TIME_UNSET
        )
        val merege = MergingMediaSource(videoSource, subtitle)
        // Prepare the player with the source.
        // Prepare the player with the source.
        simplePlayer.player = player
        player.prepare(merege)
        player.playWhenReady = true
    }
}