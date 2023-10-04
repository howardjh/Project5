package com.example.p5

import android.util.Log
import androidx.lifecycle.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateViewModel : ViewModel() {
    private val languageIdentifier = LanguageIdentification.getClient()
    private var expectedLangTag = ""
    private var translateToTag = ""
    private val enesOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()
    private val englishToSpanish = Translation.getClient(enesOptions)
    private val endeOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.GERMAN)
        .build()
    private val englishToGerman = Translation.getClient(endeOptions)
    private val esenOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.SPANISH)
        .setTargetLanguage(TranslateLanguage.ENGLISH)
        .build()
    private val spanishToEnglish = Translation.getClient(esenOptions)
    private val esdeOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.SPANISH)
        .setTargetLanguage(TranslateLanguage.GERMAN)
        .build()
    private val spanishToGerman = Translation.getClient(esdeOptions)
    private val deenOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.GERMAN)
        .setTargetLanguage(TranslateLanguage.ENGLISH)
        .build()
    private val germanToEnglish = Translation.getClient(deenOptions)
    private val deesOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.GERMAN)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()
    private val germanToSpanish = Translation.getClient(deesOptions)

    // LiveData to hold user input text
    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> = _inputText

    // LiveData to hold translated text
    private val _translatedText = MutableLiveData<String>()
    val translatedText: LiveData<String> = _translatedText

    // Function to update input text
    fun setInputText(text: String) {
        _inputText.value = text
    }
    private fun setTranslatedText(text: String) {
        _translatedText.value = text
    }

    fun setTranslateToTag(text: String){
        translateToTag = text
    }
    fun setExpectedLangTag(text: String){
        expectedLangTag = text
    }


    // Function to translate text
    fun translateText(text: String) {
        var translated = ""
        if(text.isEmpty()){ }
        else{
            if(expectedLangTag == "English" && translateToTag == "Spanish"){
                translated = englishToSpanish.translate(text).toString()
            } else if (expectedLangTag == "English" && translateToTag == "German"){
                translated = englishToGerman.translate(text).toString()
            } else if(expectedLangTag == "Spanish" && translateToTag == "English"){
                translated = spanishToEnglish.translate(text).toString()
            } else if(expectedLangTag == "Spanish" && translateToTag == "German"){
                translated = spanishToGerman.translate(text).toString()
            } else if(expectedLangTag == "German" && translateToTag == "English"){
                translated = germanToEnglish.translate(text).toString()
            } else{
                translated = germanToSpanish.translate(text).toString()
            }
        }
        setTranslatedText(translated)
    }

}
