package by.krokam.belarus_bible.app

import android.app.Application
import android.content.Context
import io.reactivex.subjects.PublishSubject
import java.util.*

private const val LANGUAGE = "LANGUAGE"
private const val PREFS_NAME = "HOLY_PREFS"

class LangManager(private val app : Application){
    enum class Language{BEL, ENG, RUS}

    val langHasChanged = PublishSubject.create<Unit>()

    var language = LangManager.Language.BEL
        set(value) {
            field = value
            when(value){
                LangManager.Language.BEL -> putString(LANGUAGE, "1")
                LangManager.Language.ENG -> putString(LANGUAGE, "3")
                LangManager.Language.RUS -> putString(LANGUAGE, "2")
            }
            langHasChanged.onNext(Unit)
        }

    val langNumber : String
        get() {
           return when(language){
                LangManager.Language.BEL -> "1"
                LangManager.Language.ENG -> "3"
                LangManager.Language.RUS -> "2"
            }
        }

    init {
        when(getString(LANGUAGE)){
            "1" -> language = LangManager.Language.BEL
            "2" -> language = LangManager.Language.ENG
            "3" -> language = LangManager.Language.RUS
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