package com.example.hopeatividadecards.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Resposta JSON da api em /cards
 */
@JsonClass(generateAdapter = true)
data class CardsPageResponse(
    @Json(name = "cards")
    val cardResponses: List<CardResponse> = listOf(),
    @Json(name = "currentPage")
    val currentPage: Int = 0,
    @Json(name = "totalPages")
    val totalPages: Int = 0
)