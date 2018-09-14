package com.nevermore.sashoolya.holybible.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nevermore.sashoolya.holybible.data.pojo.Section


@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectons(items : List<Section>)

    @Query("SELECT * FROM section")
    fun getSectons() : LiveData<List<Section>>
}
