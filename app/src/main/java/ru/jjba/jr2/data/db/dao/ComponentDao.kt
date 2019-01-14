package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Component
import ru.jjba.jr2.domain.entity.Kanji

@Dao
abstract class ComponentDao : BaseDao<Component> {
    @Query(
            // @formatter:off
            "SELECT * FROM Kanji " +
            "INNER JOIN Component AS c " +
                "ON Kanji.id = c.idKanji " +
            "WHERE c.idKanjiComponent = :kanjiId"
            // @formatter:on
    )
    abstract fun getKanjiComponents(kanjiId: Int): LiveData<List<Kanji>>
}