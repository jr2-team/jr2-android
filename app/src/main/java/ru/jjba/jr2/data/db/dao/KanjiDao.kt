package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Moji

@Dao
abstract class KanjiDao : BaseDao<Moji> {
    @Query("SELECT * FROM Moji WHERE id = :mojiId")
    abstract fun getById(mojiId: Int): LiveData<Moji>

    @Query("SELECT * FROM Moji")
    abstract fun getAll(): LiveData<List<Moji>>

    @Query(
        // @formatter:off
        "SELECT k.* FROM Moji as k " +
        "INNER JOIN ComponentOfKanjiJoin AS c " +
            "ON k.id = c.mojiComponentId " +
        "WHERE c.mojiId = :mojiId"
        // @formatter:on
    )
    abstract fun getKanjiComponents(mojiId: Int): LiveData<List<Moji>>
}