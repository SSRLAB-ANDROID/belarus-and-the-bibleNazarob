package by.krokam.belarus_bible.retrofit

import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.data.pojo.Section
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("get_cities/1/")
    fun getSections(): Observable<List<Section>>

    @GET("get_points/1/")
    fun getExpositions(): Observable<List<Exposition>>
}