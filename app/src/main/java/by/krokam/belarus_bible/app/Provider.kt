package by.krokam.belarus_bible.app

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.data.pojo.Section
import by.krokam.belarus_bible.retrofit.ApiService
import by.krokam.belarus_bible.retrofit.ApiServiceCreator
import by.krokam.belarus_bible.room.AppDB
import by.krokam.belarus_bible.room.AppDao
import by.krokam.belarus_bible.tools.Timer

class Provider(val app : Application){
    private var rootCicerone: Cicerone<Router> = Cicerone.create()
    val rootRouter = rootCicerone.router
    val rootNavigatorHolder = rootCicerone.getNavigatorHolder()

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

    var isMenuState = true
}