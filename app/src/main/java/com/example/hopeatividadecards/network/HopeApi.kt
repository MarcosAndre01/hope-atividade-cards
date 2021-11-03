package com.example.hopeatividadecards.network

import com.example.hopeatividadecards.network.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL = "https://hopeproject.herokuapp.com"
const val AUTHORIZATION_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVlZmU0NmRiYTg1NzRlN2JlNDlkYjQ4OCIsImlhdCI6MTYzNTY4NDczNiwiZXhwIjoxNjM1NzcxMTM2fQ.rfcZ6ydRzDN9SwyK5SmaqXGBSpoZ4tBd2cG3eIQLe0M"


private val moshi = Moshi.Builder().build()

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
    @Headers("Authorization:$AUTHORIZATION_TOKEN")
    @GET("cards")
    suspend fun getCards(): CardResponse

    @Headers("Authorization:$AUTHORIZATION_TOKEN")
    @GET("cards/{id}")
    suspend fun getCards(@Path("id") id: Int): List<Card>
}