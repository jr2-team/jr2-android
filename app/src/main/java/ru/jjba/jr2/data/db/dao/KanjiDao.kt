package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ru.jjba.jr2.domain.entity.Kanji
import ru.jjba.jr2.domain.entity.KanjiPart

// TODO : Улучшить работу с kanji
@Dao
abstract class KanjiDao {
    @Query("SELECT * FROM Kanji WHERE id=:id")
    abstract fun getById(id: String): Single<Kanji>

    @Query("SELECT * FROM Kanji")
    abstract fun getAll(): Flowable<List<Kanji>>

    @Query(
            "SELECT k.* " +
                    "FROM KanjiPart AS kp " +
                    "INNER JOIN Kanji AS k ON kp.kanjiPart = k.id " +
                    "WHERE kp.kanji=:kanjiId"
    )
    abstract fun getKanjiPartsByKanjiId(kanjiId: String): Flowable<List<Kanji>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(kanji: Kanji): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(kanjis: List<Kanji>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertKanjiPart(kanjiPart: KanjiPart): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertKanjiPart(kanjiParts: List<KanjiPart>): List<Long>
}