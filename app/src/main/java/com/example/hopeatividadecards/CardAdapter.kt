package com.example.hopeatividadecards

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hopeatividadecards.databinding.*
import com.example.hopeatividadecards.model.Card
import com.squareup.picasso.Picasso

class CardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TipViewHolder(val binding: CardTipItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class FactViewHolder(val binding: CardFactItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class MotivationalViewHolder(val binding: CardMotivationalItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ArticleViewHolder(val binding: CardArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class SuccessHistoryViewHolder(val binding: CardSuccesshistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class VideoViewHolder(val binding: CardVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class OpinionViewHolder(val binding: CardOpinionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class QuestionViewHolder(val binding: CardQuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var cards: List<Card>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemViewType(position: Int): Int {
        val card = cards[position]

        return when (card) {
            is Card.Tip -> R.layout.card_tip_item
            is Card.Fact -> R.layout.card_fact_item
            is Card.Motivational -> R.layout.card_motivational_item
            is Card.Article -> R.layout.card_article_item
            is Card.SuccessHistory -> R.layout.card_successhistory_item
            is Card.Video -> R.layout.card_video_item
            is Card.Opinion -> R.layout.card_opinion_item
            is Card.Question -> R.layout.card_question_item
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.card_tip_item -> TipViewHolder(
                CardTipItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_fact_item -> FactViewHolder(
                CardFactItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_motivational_item -> MotivationalViewHolder(
                CardMotivationalItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_article_item -> ArticleViewHolder(
                CardArticleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_successhistory_item -> SuccessHistoryViewHolder(
                CardSuccesshistoryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_video_item -> VideoViewHolder(
                CardVideoItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_opinion_item -> OpinionViewHolder(
                CardOpinionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.card_question_item -> QuestionViewHolder(
                CardQuestionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Illegal viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TipViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Tip
                tipTitle.text = card.title
                tipText.text = card.message
            }

            is FactViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Fact
                factTitle.text = card.title
                factText.text = card.message
            }

            is MotivationalViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Motivational
                motivationalText.text = card.quote
            }

            is ArticleViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Article

                articleTitle.text = card.title

                if (card.imageUrl.isNotEmpty()) {
                    Picasso.get()
                        .load(card.imageUrl)
                        .into(articleImage)
                }
            }

            is SuccessHistoryViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.SuccessHistory

                historyTitle.text = card.title
                historyDescription.text = card.description

                if (card.imageUrl.isNotEmpty()) {
                    Picasso.get()
                        .load(card.imageUrl)
                        .into(historyImage)
                }
            }

            is VideoViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Video
                videoTitle.text = card.title

                if (card.imageUrl.isNotEmpty()) {
                    Picasso.get()
                        .load(card.imageUrl)
                        .into(videoThumbnail)
                }
            }

            is OpinionViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Opinion
                opinionTitle.text = card.specialist
                opinionDescription.text = card.description
                opinionButton.text =
                    holder.itemView.context.getString(R.string.opinion_button) + " ${card.specialist}"

                if (card.imageUrl.isNotEmpty()) {
                    Picasso.get()
                        .load(card.imageUrl)
                        .into(specialistImage)
                }
            }

            is QuestionViewHolder -> holder.binding.apply {
                val card = cards[position] as Card.Question

                questionTitle.text = card.title
                questionDescription.text = card.description
                if (card.isFromCommunity) {
                    questionType.text =
                        holder.itemView.context.getString(R.string.community_question)
                } else {
                    questionType.text =
                        holder.itemView.context.getString(R.string.frequent_question)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return cards.size
    }
}