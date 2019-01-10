package ru.jjba.jr2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

@Database(
        entities = [
            Word::class
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao

    companion object {
        private const val DB_NAME = "jr2.db"

        //TODO : Заполнение базы при первом лаунче
        fun create(context: Context, memoryOnly: Boolean) = if (memoryOnly) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
        } else {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
        }.build()
    }
}