package ru.jjba.jr2

import android.support.multidex.MultiDexApplication
import com.google.gson.GsonBuilder
import ru.jjba.jr2.data.Serializer
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word

class App : MultiDexApplication() {
    lateinit var db: AppDatabase

    val gson = GsonBuilder()
            .registerTypeAdapter(Word::class.java, Serializer.word)
            .registerTypeAdapter(Interp::class.java, Serializer.interpretation)
            .create()

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = AppDatabase.create(context = this, memoryOnly = false)
    }

    companion object {
        lateinit var instance: App
    }
}