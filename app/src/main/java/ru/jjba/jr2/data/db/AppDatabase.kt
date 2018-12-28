package ru.jjba.jr2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.jjba.jr2.data.db.dao.*
import ru.jjba.jr2.domain.entity.*

@Database(
        entities = [
            Word::class,
            Interpretation::class,
            Example::class,
            Kanji::class,
            KanjiPart::class,
            Kana::class
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao
    abstract fun getInterpDao(): InterpDao
    abstract fun getExampleDao(): ExampleDao
    abstract fun getKanjiDao(): KanjiDao
    abstract fun getKanaDao(): KanaDao

    companion object {
        private const val DB_NAME = "jr2.db"
        private const val PREPOPULATE_DATA = "word.json"

        fun create(context: Context, memoryOnly: Boolean) = if (memoryOnly) {
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
        } else {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            /*
                             * Prepopulate data from json to local db, takes some time
                             * so need to show loader while data is loading.
                             * Need to move code below to other layer with loader render
                             */
                            //TODO : Разобраться с ключами в таблицах и порядком слов
                            /*KanaInteractor().insertPiecesOfKana(
                                    Gson().fromJson<List<Kana>>(
                                            context.loadJsonFromAsset("kana.json"),
                                            object : TypeToken<List<Kana>>() {}.type
                                    )
                            ).observeOn(AndroidSchedulers.mainThread())
                                    .subscribeBy {
                                        WordInteractor().insert(
                                                Gson().fromJson<List<Word>>(
                                                        context.loadJsonFromAsset(PREPOPULATE_DATA),
                                                        object : TypeToken<List<Word>>() {}.type
                                                )
                                        ).observeOn(AndroidSchedulers.mainThread())
                                                .subscribeBy { }
                                    }*/
                        }
                    })
        }.build()
    }
}