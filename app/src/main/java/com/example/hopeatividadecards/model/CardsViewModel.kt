package com.example.hopeatividadecards.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopeatividadecards.network.CardResponse
import com.example.hopeatividadecards.network.HopeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val TAG = "CardsViewModel"

/**
 * [ViewModel] responsável por expor a lista de [Card]s a ser mostrada na UI
 */
class CardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow(listOf<Card>())

    /**
     * StateFlow que expõe a lista de [Card]s
     */
    val cards = _cards.asStateFlow()

    init {
        getCards()
    }

    /**
     * Converte a lista de [CardResponse] da requisição GET /cards em uma lista de [Card] que possa
     * ser exibida pela View
     */
    private fun getCards() {
        viewModelScope.launch {
            val authToken = HopeApi.retrofitService.getAuthToken().token
            val cardsResponse =
                HopeApi.retrofitService.getCards(authToken = authToken).cardResponses
            val cardsList = mutableListOf<Card>()

            for (networkCard in cardsResponse) {
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