package by.krokam.belarus_bible.tools

import android.os.CountDownTimer
import io.reactivex.subjects.PublishSubject

class Timer() {

    var isStarted = false
        private set

    var isRunning = false
        private set

    var isComplited = false
        private set

    private var time = 0
    private var timer: CountDownTimer? = null

    val onComplite = PublishSubject.create<Boolean>()

    fun start(time : Int) : Boolean {
        if (!isStarted) {
            if (!isComplited) {
                this.time = time
                isStarted = true
                isRunning = true
                startTimer()

                return true
            }
        }
        return false
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer((time * 1000).toLong(), 1000) {
            override fun onTick(l: Long) {
                time -= 1
            }

            override fun onFinish() {
                onComplite.onNext(true)
                onComplite.onComplete()
                isComplited = true
                isRunning = false
                isStarted = false
            }
        }
        timer?.start()
    }

    fun resume() {
        if (isStarted) {
            if (!isRunning) {
                isRunning = true

                if (time > 0) {
                    startTimer()
                }
            }
        }
    }

    fun pause() {
        if (isStarted) {
            if (isRunning) {
                isRunning = false
                timer?.cancel()
                timer = null
            }
        }
    }
}

