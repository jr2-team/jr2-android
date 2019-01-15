package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Kanji

@Dao
abstract class KanjiDao : BaseDao<Kanji> {
    @Query("SELECT * FROM Kanji WHERE id = :kanjiId")
    abstract fun getById(kanjiId: Int): LiveData<Kanji>

    @Query("SELECT * FROM Kanji")
    abstract fun getAll(): LiveData<List<Kanji>>

    @Query(
            // @formatter:off
            "SELECT k.* FROM Kanji as k " +
            "INNER JOIN Component AS c " +
                "ON k.id = c.kanjiIdComp " +
            "WHERE c.kanjiId = :kanjiId"
            // @formatter:on
    )
    abstract fun getKanjiComponents(kanjiId: Int): LiveData<List<Kanji>>
}