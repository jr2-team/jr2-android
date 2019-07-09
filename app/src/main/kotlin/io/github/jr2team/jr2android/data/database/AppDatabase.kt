package io.github.jr2team.jr2android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.jr2team.jr2android.data.database.dao.KanjiDao
import io.github.jr2team.jr2android.domain.room_entities.entity.Kanji

@Database(
    entities = [
        Kanji::class
    ],
    version = DbProviderFromAssets.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getKanjiDao(): KanjiDao

    companion object {
        fun create(context: Context, memoryOnly: Boolean) = if (memoryOnly) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
        } else {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DbProviderFromAssets.DATABASE_NAME
            )
        }.build()
    }
}