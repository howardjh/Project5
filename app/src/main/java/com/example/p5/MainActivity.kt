package com.example.p5

import com.example.p5.databinding.ActivityMainBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TranslateViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var inputFragment = supportFragmentManager.findFragmentById(R.id.inputFragment)
        inputFragment?.let {
            // it.javaClass = InputFragment::class.java
        }

        var lastTranslateTime = 0L
        viewModel.inputText.observeForever() { text ->
            val now = System.currentTimeMillis()
            if (now - lastTranslateTime >= 500) {
                viewModel.translateText(text)
                lastTranslateTime = now
            }
        }

        binding.inputEnglish.setOnClickListener {
            viewModel.setExpectedLangTag("English")
        }

        binding.inputSpanish.setOnClickListener {
            viewModel.setExpectedLangTag("Spanish")
        }

        binding.inputGerman.setOnClickListener {
            viewModel.setExpectedLangTag("German")
        }

        binding.translateEnglish.setOnClickListener {
            viewModel.setTranslateToTag("English")
        }

        binding.translateGerman.setOnClickListener {
            viewModel.setTranslateToTag("German")
        }

        binding.translateSpanish.setOnClickListener {
            viewModel.setTranslateToTag("Spanish")
        }

        viewModel.translatedText.observe(this) { translatedText ->
            binding.tvTranslated.text = translatedText
        }

    }

}

