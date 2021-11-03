package com.example.hopeatividadecards.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardResponse(
    @Json(name = "cards")
    val cards: List<Card> = listOf(),
    @Json(name = "currentPage")
    val currentPage: Int = 0,
    @Json(name = "totalPages")
    val totalPages: Int = 0
)