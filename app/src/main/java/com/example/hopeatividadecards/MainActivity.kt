package com.example.hopeatividadecards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopeatividadecards.databinding.ActivityMainBinding
import com.example.hopeatividadecards.model.Card
import com.example.hopeatividadecards.model.CardsViewModel
import com.example.hopeatividadecards.network.HopeApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

/**
 * Única tela da aplicação, contendo um [RecyclerView] mostrando uma lista com cards de diferentes
 * layouts vindos da API da Hope.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cardAdapter = CardAdapter()
        binding.recyclerView.adapter = cardAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Coleta a lista de cards do viewModel e atualiza a lista no recyclerView.
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cards.collect { cards ->
                    cardAdapter.cards = cards
                }
            }

        }

    }
}

