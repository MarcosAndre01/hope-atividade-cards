package com.example.hopeatividadecards.network

import com.example.hopeatividadecards.network.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://hopeproject.herokuapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object HopeApi {
    val retrofitService: HopeApiService by lazy {
        retrofit.create(HopeApiService::class.java)
    }
}

interface HopeApiService {
    @GET("/users/anonymous")
    suspend fun getAuthToken(): AuthResponse

    @GET("cards")
    suspend fun getCards(
        @Query("limit") limit: Int = 999,
        @Header("Authorization") authToken:  String
    ): CardResponse
}
