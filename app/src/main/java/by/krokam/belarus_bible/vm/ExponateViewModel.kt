package by.krokam.belarus_bible.vm

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import by.krokam.belarus_bible.R
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.databinding.FragmentExponateBinding
import kotlinx.coroutines.*

class ExponateViewModel : ViewModel() {

    private var exponate: Exposition? = null
    private var _binding: FragmentExponateBinding? = null
    private var configuration: Int = 0

    private var mpStatus = "play"
    private var viewModelPlayerStatus = 0

    private val mediaJob = Job()
    private val mediaScope = CoroutineScope(Dispatchers.Main + mediaJob)
    private var mediaPlayer: MediaPlayer? = null

    fun getExponate() = exponate

    fun setExponate(exponate: Exposition) {
        this.exponate = exponate
    }

    fun initializeMediaPlayer(uri: Uri, binding: FragmentExponateBinding, context: Context) {
        if (viewModelPlayerStatus == 0) {

            mpStatus = "play"

            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setDataSource(context, uri)
            mediaPlayer!!.prepare()

            binding.apPlayProgress.max = mediaPlayer!!.duration
            binding.apPlayTimeMax.text = convertToTimerMode(mediaPlayer!!.duration)
            binding.apPlayButton.setImageResource(R.drawable.ic_play_button)
            binding.apPlayProgress.progress = 0

            pbListener(binding, mediaPlayer!!)

            viewModelPlayerStatus = 1
        }
    }

    fun playAudio(binding: FragmentExponateBinding) {
        instructMediaPlayer(binding)
    }

    private fun mpStop() {
        mpStatus = "stop"

        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        } else mediaPlayer!!.release()
    }

    fun mpRefresh(){

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        } else mediaPlayer!!.release()

        viewModelPlayerStatus = 0
    }

    private fun instructMediaPlayer(binding: FragmentExponateBinding) {

        mediaScope.launch {
            when (mpStatus) {

                "pause" -> {
                    mediaPlayer!!.pause()
                    binding.apPlayButton.setImageResource(R.drawable.ic_play_button)
                    mpStatus = "continue"
                }
                "continue" -> {

                    mediaPlayer!!.start()
                    binding.apPlayButton.setImageResource(R.drawable.ic_pause_button)
                    mpStatus = "pause"

                    mediaScope.launch {
                        progressListener(binding, mediaPlayer!!)
                    }
                }
                "play" -> {

                    try {

                        mediaPlayer!!.start()

                        mediaScope.launch {
                            progressListener(binding, mediaPlayer!!)
                        }

                        binding.apPlayButton.setImageResource(R.drawable.ic_pause_button)
                        mpStatus = "pause"

                    } catch (e: Exception) {
                        Log.e("Media player error", e.message.toString())
                    }
                }
            }
        }
    }

    private fun convertToTimerMode(duration: Int): String {
        val minute = duration % (1000 * 60 * 60) / (1000 * 60)
        val seconds = duration % (1000 * 60 * 60) % (1000 * 60) / 1000

        var finalString = ""
        finalString += "$minute:"
        if (seconds < 10) finalString += "0"
        finalString += "$seconds"

        return finalString
    }

    private suspend fun progressListener(
        binding: FragmentExponateBinding,
        mediaPlayer: MediaPlayer
    ) {

        while (mpStatus == "pause") {
            binding.apCurrentPlayTime.text = convertToTimerMode(mediaPlayer.currentPosition)
            binding.apPlayProgress.progress = mediaPlayer.currentPosition
            delay(250)

            if (binding.apPlayProgress.progress == binding.apPlayProgress.max) {
                mpStatus = "play"
                delay(250)
                binding.apPlayButton.setImageResource(R.drawable.ic_play_button)
                mediaPlayer.seekTo(0)
                binding.apPlayProgress.progress = 0
                binding.apCurrentPlayTime.text = convertToTimerMode(mediaPlayer.currentPosition)
            }
        }
    }

    private fun pbListener(binding: FragmentExponateBinding, mediaPlayer: MediaPlayer) {

        binding.apPlayProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer.seekTo(p1)
                    binding.apCurrentPlayTime.text = convertToTimerMode(mediaPlayer.currentPosition)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    fun saveBinding(mBinding: FragmentExponateBinding){
        _binding = mBinding
    }

    fun checkBindingSaved(): Boolean{
        return _binding == null
    }

    fun getBinding() = _binding

    fun saveConfig(configuration: Int){
        this.configuration = configuration
    }

    fun getConfig() = configuration

    override fun onCleared() {
        super.onCleared()
        mpStop()
    }
}