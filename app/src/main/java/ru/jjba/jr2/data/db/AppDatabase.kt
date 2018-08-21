package ru.jjba.jr2.data.db

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.jjba.jr2.data.db.dao.WordDao

abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao

    companion object {
        private const val DB_NAME = "jr2.db"

        fun create(context: Context, memoryOnly: Boolean) = if (memoryOnly) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
        } else {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
        }.build()
    }
}