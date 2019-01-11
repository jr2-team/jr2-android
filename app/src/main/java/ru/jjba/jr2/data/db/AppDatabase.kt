package ru.jjba.jr2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

@Database(
        entities = [
            Word::class
        ],
        version = ActsDbHelper.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao

    companion object {
        fun create(context: Context, memoryOnly: Boolean) = if (memoryOnly) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
        } else {
            Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    ActsDbHelper.DATABASE_NAME
            )
        }.build()
    }
}