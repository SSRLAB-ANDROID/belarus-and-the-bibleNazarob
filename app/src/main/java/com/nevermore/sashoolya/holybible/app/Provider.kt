package com.nevermore.sashoolya.holybible.app

import android.app.Application
import android.arch.persistence.room.Room
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.retrofit.ApiService
import com.nevermore.sashoolya.holybible.retrofit.ApiServiceCreator
import com.nevermore.sashoolya.holybible.room.AppDB
import com.nevermore.sashoolya.holybible.room.AppDao
import com.nevermore.sashoolya.holybible.tools.Timer
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class Provider(val app : Application){
    private var rootCicerone: Cicerone<Router> = Cicerone.create()
    val rootRouter = rootCicerone.router
    val rootNavigatorHolder = rootCicerone.navigatorHolder

    private var _apiService : ApiService? = null
    val apiService : ApiService
        get() {
            if(_apiService == null)
                _apiService = ApiServiceCreator.createService(ApiService::class.java)
            return _apiService!!
        }

    private var _db : AppDB? = null
    private val db : AppDB
        get() {
            if(_db == null)
                _db = Room.databaseBuilder(app, AppDB::class.java, AppDB.name).build()
            return _db!!
        }

    private var _userDao : AppDao? = null
    val appDao : AppDao
        get() {
            if(_userDao == null)
                _userDao = db.appDao
            return _userDao!!
        }

    var selectedSection : Section? = null
    var selectedExposition : Exposition? = null
    var expos : List<Exposition>? = null
    var secs : List<Section>? = null

    val timer = Timer()
    val langManager = LangManager(app)
}