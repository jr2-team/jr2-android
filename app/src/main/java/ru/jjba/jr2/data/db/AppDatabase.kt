package ru.jjba.jr2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.jjba.jr2.data.db.dao.*
import ru.jjba.jr2.domain.entity.*
import ru.jjba.jr2.domain.join.ComponentOfKanjiJoin
import ru.jjba.jr2.domain.join.WordInSentenceJoin

@Database(
        entities = [
            Kana::class,
            Moji::class,
            Word::class,
            Sentence::class,
            ComponentOfKanjiJoin::class,
            WordInSentenceJoin::class,
            Group::class
        ],
        version = DbProviderFromAssets.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getKanaDao(): KanaDao
    abstract fun getKanjiDao(): KanjiDao
    abstract fun getWordDao(): WordDao
    abstract fun getSentenceDao(): SentenceDao
    abstract fun getGroupDao(): GroupDao

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