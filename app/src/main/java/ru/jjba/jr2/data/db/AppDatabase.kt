package ru.jjba.jr2.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.data.db.dao.*
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.*
import ru.jjba.jr2.utils.loadJSONFromAsset

@Database(
        entities = [
            Word::class,
            Interpretation::class,
            Example::class,
            Kanji::class,
            KanjiPart::class,
            JpSound::class
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao
    abstract fun getInterpretationDao(): InterpretationDao
    abstract fun getExampleDao(): ExampleDao
    abstract fun getKanjiDao(): KanjiDao
    abstract fun getJpSoundDao(): JpSoundDao

    companion object {
        private const val DB_NAME = "jr2.db"
        private const val PREPOPULATE_DATA = "words.json"

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
                            WordDbRepository().insert(
                                    Gson().fromJson<List<Word>>(
                                            context.loadJSONFromAsset(PREPOPULATE_DATA),
                                            object : TypeToken<List<Word>>() {}.type
                                    )
                            ).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeBy { }
                        }
                    })
        }.build()
    }
}