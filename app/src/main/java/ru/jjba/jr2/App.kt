package ru.jjba.jr2

import android.speech.tts.TextToSpeech
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.google.gson.GsonBuilder
import ru.jjba.jr2.data.Serializer
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word
import java.util.*

class App : MultiDexApplication(), TextToSpeech.OnInitListener {
    lateinit var db: AppDatabase
    lateinit var tts: TextToSpeech

    val gson = GsonBuilder()
            .registerTypeAdapter(Word::class.java, Serializer.word)
            .registerTypeAdapter(Interp::class.java, Serializer.interpretation)
            .create()
    
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.JAPANESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = AppDatabase.create(context = this, memoryOnly = false)
        tts = TextToSpeech(this, this)
    }

    companion object {
        lateinit var instance: App
    }
}