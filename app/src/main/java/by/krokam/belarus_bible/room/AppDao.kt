package by.krokam.belarus_bible.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.data.pojo.Section

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectons(items : List<Section>)

    @Query("SELECT * FROM section")
    fun getSectons() : LiveData<List<Section>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpositions(items : List<Exposition>)

    @Query("SELECT * FROM exposition WHERE cityId = :cityID")
    fun getExpositions(cityID : String) : LiveData<List<Exposition>>
}
