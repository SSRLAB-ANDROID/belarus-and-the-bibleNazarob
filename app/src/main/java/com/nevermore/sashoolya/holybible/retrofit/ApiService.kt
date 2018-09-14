package com.nevermore.sashoolya.holybible.retrofit

import com.nevermore.sashoolya.holybible.data.pojo.Section
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("get_cities/1/")
    fun getSections(): Observable<List<Section>>

    @GET("get_points/1/")
    fun getExposites(): Response<ResponseBody>
}