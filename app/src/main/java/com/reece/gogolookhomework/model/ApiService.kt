package com.reece.gogolookhomework.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("/api")
    fun search(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") imageType: String) : Deferred<Response<Model.Result>>
}