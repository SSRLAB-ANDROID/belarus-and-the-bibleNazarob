package com.nevermore.sashoolya.holybible.app

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
        var INSTANCE : com.nevermore.sashoolya.holybible.app.Application? = null
    }
}