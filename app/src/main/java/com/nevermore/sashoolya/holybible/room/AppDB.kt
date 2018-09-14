package com.nevermore.sashoolya.holybible.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.data.pojo.Section


@Database(entities = [Section::class, Exposition::class],
        version = 1, exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract val appDao : AppDao
    companion object {
        var name = "bible_db_v1"
    }
}
