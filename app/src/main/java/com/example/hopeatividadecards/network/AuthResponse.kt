package com.example.hopeatividadecards.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Resposta JSON da api em /users/anonymous
 */
@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "token")
    val token: String = ""
)