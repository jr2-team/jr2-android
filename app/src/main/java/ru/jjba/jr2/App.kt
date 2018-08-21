package ru.jjba.jr2

import android.app.Application
import ru.jjba.jr2.data.db.AppDatabase

class App : Application() {
    lateinit var room: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this

        room = AppDatabase.create(context = this, memoryOnly = false)
    }

    companion object {
        lateinit var instance: App
    }
}