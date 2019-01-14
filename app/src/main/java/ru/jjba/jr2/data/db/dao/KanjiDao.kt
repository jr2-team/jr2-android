package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import ru.jjba.jr2.domain.entity.Kanji

@Dao
abstract class KanjiDao : BaseDao<Kanji> {
    @Query("SELECT * FROM Kanji WHERE id = :kanjiId")
    abstract fun getById(kanjiId: Int): Single<Kanji>

    @Query("SELECT * FROM Kanji")
    abstract fun getAll(): LiveData<List<Kanji>>
}