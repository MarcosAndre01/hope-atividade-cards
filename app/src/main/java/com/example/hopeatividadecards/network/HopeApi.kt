package com.example.hopeatividadecards.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

/**
 * Serviço do retrofit que permite fazer requisições à API do Hope
 */

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

/**
 * Interface com as requisições que podem ser feitas à API
 */
interface HopeApiService {
    @GET("/users/anonymous")
    suspend fun getAuthToken(): AuthResponse

    @GET("cards")
    suspend fun getCards(
        @Query("limit") limit: Int = 999,
        @Header("Authorization") authToken:  String
    ): CardsPageResponse
}
