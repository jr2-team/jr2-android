package io.github.jr2team.jr2android

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.multidex.MultiDexApplication
import com.squareup.moshi.Moshi
import io.github.jr2team.jr2android.data.database.AppDatabase
import okio.buffer
import okio.source
import java.util.*

class App : MultiDexApplication(), TextToSpeech.OnInitListener {
    lateinit var db: AppDatabase
    lateinit var moshi: Moshi

    fun readAsset(assetName: String): String {
        var content = String()
        assets.open(assetName).use { stream ->
            content = stream.source()
                .buffer()
                .readUtf8()
                .filterNot { it == '\uFEFF' }
        }
        return content
    }

    override fun onInit(status: Int) {}

    override fun onCreate() {
        super.onCreate()
        instance = this
        // TODO : Заполнение базы при первом заупуске
        db = AppDatabase.create(context = this, memoryOnly = false)
        moshi = Moshi.Builder().build()
    }

    companion object {
        lateinit var instance: App
    }
}