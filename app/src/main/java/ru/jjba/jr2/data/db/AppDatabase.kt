package ru.jjba.jr2.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.jjba.jr2.data.db.dao.ExampleDao
import ru.jjba.jr2.data.db.dao.InterpretationDao
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Example
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word

@Database(
        entities = [
            Word::class,
            Interpretation::class,
            Example::class
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao
    abstract fun getInterpretationDao(): InterpretationDao
    abstract fun getExample(): ExampleDao

    companion object {
        private const val DB_NAME = "jr2.db"

        fun create(context: Context, memoryOnly: Boolean) = if (memoryOnly) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
        } else {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
        }.build()
    }
}