package com.example.hopeatividadecards.model

/**
 * Tipo de dado para representar o [Card] a ser exibido na View.
 */
sealed class Card() {
    abstract val id: String

    data class Tip(override val id: String, val title: String, val message: String) : Card()

    data class Fact(override val id: String, val title: String, val message: String) : Card()

    data class Motivational(override val id: String, val quote: String) : Card()

    data class Article(
        override val id: String,
        val title: String,
        val description: String,
        val imageUrl: String,
        val linkUrl: String
    ) : Card()

    data class SuccessHistory(
        override val id: String,
        val title: String,
        val description: String,
        val imageUrl: String,
        val linkUrl: String
    ) : Card()

    data class Video(
        override val id: String,
        val title: String,
        val description: String,
        val imageUrl: String,
        val linkUrl: String
    ) : Card()

    data class Opinion(
        override val id: String,
        val specialist: String,
        val description: String,
        val imageUrl: String
    ) : Card()

    data class Question(
        override val id: String,
        val isFromCommunity: Boolean,
        val title: String,
        val description: String
    ) : Card()

}