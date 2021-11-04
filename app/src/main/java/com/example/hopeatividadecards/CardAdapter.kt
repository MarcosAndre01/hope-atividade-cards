package com.example.hopeatividadecards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hopeatividadecards.databinding.CardAdviceItemBinding
import com.example.hopeatividadecards.model.Card

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(val binding: CardAdviceItemBinding) :
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
        set(value) { differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(CardAdviceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.binding.apply{
            val card = cards.get(position) as Card.Advice
            adviceTitle.text = card.title
            adviceText.text = card.message
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}