package com.example.hopeatividadecards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.hopeatividadecards.databinding.ActivityMainBinding
import com.example.hopeatividadecards.network.HopeApi

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            val response = HopeApi.retrofitService.getCards(0)
            binding.textView.text = response[0].title
        }

    }
}