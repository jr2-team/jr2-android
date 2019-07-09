package io.github.jr2team.jr2android.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.room_entities.entity.Kanji

@Dao
abstract class KanjiDao : BaseDao<Kanji> {
    @Query("SELECT * FROM Kanji WHERE id = :kanjiId")
    abstract suspend fun getById(kanjiId: Int): Kanji

    @Query("SELECT * FROM Kanji")
    abstract suspend fun getAll(): List<Kanji>
}