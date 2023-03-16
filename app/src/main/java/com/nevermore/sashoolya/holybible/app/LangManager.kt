package com.nevermore.sashoolya.holybible.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import io.reactivex.subjects.PublishSubject
import java.util.*

private const val LANGUAGE = "LANGUAGE"
private const val PREFS_NAME = "HOLY_PREFS"

class LangManager(private val app : Application){
    enum class Language{BEL, ENG, RUS}

    val langHasChanged  = PublishSubject.create<Unit>()

    var language = Language.BEL
        set(value) {
            field = value
            //updateResources(value)
            when(value){
                Language.BEL -> putString(LANGUAGE, "1")
                Language.ENG -> putString(LANGUAGE, "3")
                Language.RUS -> putString(LANGUAGE, "2")
            }
            langHasChanged.onNext(Unit)
        }

    val langNumber : String
        get() {
           return when(language){
                Language.BEL -> "1"
                Language.ENG -> "3"
                Language.RUS -> "2"
            }
        }

    init {
        //updateResources(Language.BEL)
        when(getString(LANGUAGE)){
            "1" -> language = Language.BEL
            "2" -> language = Language.ENG
            "3" -> language = Language.RUS
        }
    }


    fun getLocale() : Locale {
        return when(language){
            Language.ENG -> Locale.ENGLISH
            Language.BEL -> Locale("be")
            Language.RUS -> Locale("ru")
        }
    }

    private fun getString(name: String): String? {
        return app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(name, "")
    }

    private fun putString(name: String, value: String) {
        app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().putString(name, value).apply()
    }
}