package com.nevermore.sashoolya.holybible.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceCreator {
    private val BASE_URL = "http://belarusandthebible.krokam.by/api/"

    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                //.addNetworkInterceptor(StethoInterceptor())
                //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                //.addInterceptor(HeaderInterceptor())
                .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return builder.client(getClient()).build().create(serviceClass)
    }
}
