package ru.jjba.jr2

import android.app.Application
import android.support.multidex.MultiDexApplication
import ru.jjba.jr2.data.db.AppDatabase

class App : MultiDexApplication() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = AppDatabase.create(context = this, memoryOnly = false)
    }

    companion object {
        lateinit var instance: App
    }
}