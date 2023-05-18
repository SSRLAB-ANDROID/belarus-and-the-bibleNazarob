package by.krokam.belarus_bible.app

import android.app.Application
import com.facebook.stetho.Stetho

class Application : Application(){

    lateinit var provider: Provider

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Stetho.initializeWithDefaults(this)
        provider = Provider(this)
    }

    companion object {
        var INSTANCE : by.krokam.belarus_bible.app.Application? = null
    }
}