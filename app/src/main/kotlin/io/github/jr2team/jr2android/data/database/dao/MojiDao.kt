package io.github.jr2team.jr2android.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.room_entities.entity.Moji

@Dao
abstract class MojiDao : BaseDao<Moji> {
    @Query("SELECT * FROM Moji WHERE id = :mojiId")
    abstract fun getById(mojiId: Int): Moji

    @Query("SELECT * FROM Moji")
    abstract suspend fun getAll(): List<Moji>

    @Query("""
        SELECT k.* 
        FROM Moji as k 
            INNER JOIN ComponentOfKanjiJoin AS c ON k.id = c.mojiComponentId 
        WHERE c.mojiId = :mojiId"""
    )
    abstract suspend fun getKanjiComponents(mojiId: Int): List<Moji>
}