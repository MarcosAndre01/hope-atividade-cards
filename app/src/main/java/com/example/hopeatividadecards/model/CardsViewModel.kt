package com.example.hopeatividadecards.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopeatividadecards.network.HopeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val TAG = "CardsViewModel"

class CardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow(listOf<Card>());
    val cards = _cards.asStateFlow()

    init {
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            val getCardsResponse = HopeApi.retrofitService.getCards(6)
            val cardsList = mutableListOf<Card>()

            for (networkCard in getCardsResponse) {
                val newCard: Card = when (networkCard.cardTypeID) {
                    0 -> Card.Tip(
                        networkCard.id, networkCard.title, networkCard.description
                    )
                    1 -> Card.Fact(
                        networkCard.id, networkCard.title, networkCard.description
                    )
                    2 -> Card.Motivational(
                        networkCard.id, networkCard.description
                    )
                    3 -> Card.Article(
                        networkCard.id,
                        networkCard.title,
                        networkCard.description,
                        networkCard.imageUrl,
                        networkCard.linkUrl
                    )
                    4 -> Card.SuccessHistory(
                        networkCard.id,
                        networkCard.title,
                        networkCard.description,
                        networkCard.imageUrl,
                        networkCard.linkUrl
                    )
                    5 -> Card.Video(
                        networkCard.id,
                        networkCard.title,
                        networkCard.description,
                        networkCard.imageUrl,
                        networkCard.linkUrl
                    )
                    6 -> Card.Opinion(
                        networkCard.id,
                        networkCard.title,
                        networkCard.description,
                        networkCard.imageUrl
                    )
                    7 -> Card.Question(
                        networkCard.id,
                        true,
                        networkCard.title,
                        networkCard.description
                    )
                    8 -> Card.Question(
                        networkCard.id,
                        false,
                        networkCard.title,
                        networkCard.description
                    )
                    else -> {
                        Log.d(TAG, "getCards: Invalid cardTypeID: ${networkCard.cardTypeID}")
                        continue
                    }
                }

                cardsList.add(newCard)
            }

            _cards.value = cardsList
        }
    }

}