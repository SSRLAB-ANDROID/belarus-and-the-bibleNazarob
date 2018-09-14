package com.nevermore.sashoolya.holybible.data.pojo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity
class Exposition(
        @PrimaryKey
        @NonNull
        var id : String = "",
        @SerializedName("id_locale")
        var idLocale : String = "",
        var name : String = "",
        var lang : String = "",
        var logo : String = "",
        @SerializedName("last_edit_time")
        var lastEdittime : String = "",
        var visible : String = ""
)