package com.nevermore.sashoolya.holybible.data.pojo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity
class Exposition(
        @PrimaryKey
        @NonNull
        var id : String = "",
        @SerializedName("id_point")
        var idPoint : String = "",
        var name : String = "",
        @SerializedName("point_museum")
        var pointMuseum : String = "",
        var text : String = "",
        var sound : String = "",
        var lang : String = "",
        @SerializedName("last_edit_time")
        var lastEdittime : String = "",
        var logo : String = "",
        var photo : String = "",
        @SerializedName("city_id")
        var cityId : String = "",
        @Ignore
        var images : List<String> = listOf(),
        var image : String = ""
)