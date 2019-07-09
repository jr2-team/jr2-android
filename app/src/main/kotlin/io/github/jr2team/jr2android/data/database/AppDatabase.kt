package io.github.jr2team.jr2android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.jr2team.jr2android.data.database.dao.*
import io.github.jr2team.jr2android.domain.room_entities.entity.*
import io.github.jr2team.jr2android.domain.room_entities.join.ComponentOfKanjiJoin
import io.github.jr2team.jr2android.domain.room_entities.join.GroupOfWordsJoin
import io.github.jr2team.jr2android.domain.room_entities.join.WordInSentenceJoin

@Database(
    entities = [
        Kana::class,
        Moji::class,
        Word::class,
        Sentence::class,
        ComponentOfKanjiJoin::class,
        WordInSentenceJoin::class,
        Group::class,
        GroupOfWordsJoin::class,
        Section::class
    ],
    version = DbProviderFromAssets.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getKanaDao(): KanaDao
    abstract fun getMojiDao(): MojiDao
    abstract fun getWordDao(): WordDao
    abstract fun getSentenceDao(): SentenceDao
    abstract fun getGroupDao(): GroupDao
    abstract fun getSectionDao(): SectionDao

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