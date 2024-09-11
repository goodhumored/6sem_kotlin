package com.example.lr1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lr1.databinding.ActivityMotorcyclistBinding

class Motorcyclist : AppCompatActivity() {

    private lateinit var binding: ActivityMotorcyclistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMotorcyclistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}