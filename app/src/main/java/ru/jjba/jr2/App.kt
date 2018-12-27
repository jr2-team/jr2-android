package ru.jjba.jr2

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.multidex.MultiDexApplication
import ru.jjba.jr2.data.db.AppDatabase
import java.util.*

class App : MultiDexApplication(), TextToSpeech.OnInitListener {
    lateinit var db: AppDatabase
    lateinit var tts: TextToSpeech

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.JAPANESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
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