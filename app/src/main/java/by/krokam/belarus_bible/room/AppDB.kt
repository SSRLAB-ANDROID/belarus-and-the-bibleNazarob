package by.krokam.belarus_bible.room

import androidx.room.Database
import androidx.room.RoomDatabase
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.data.pojo.Section

@Database(entities = [Section::class, Exposition::class],
        version = 1, exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract val appDao : AppDao
    companion object {
        var name = "bible_db_v1"
    }
}
