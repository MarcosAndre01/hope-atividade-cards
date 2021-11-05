package com.example.hopeatividadecards.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Representação JSON dos Cards nas respostas da API
 */
@JsonClass(generateAdapter = true)
data class CardResponse(
    @Json(name = "cardTypeID")
    val cardTypeID: Int = 0,
    @Json(name = "clicks")
    val clicks: Int = 0,
    @Json(name = "createdAt")
    val createdAt: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "_id")
    val id: String = "",
    @Json(name = "image_url")
    val imageUrl: String = "",
    @Json(name = "link_url")
    val linkUrl: String = "",
    @Json(name = "specialistID")
    val specialistID: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "user_id")
    val userId: String = "",
    @Json(name = "__v")
    val v: Int = 0,
    @Json(name = "validated")
    val validated: Boolean = false
)