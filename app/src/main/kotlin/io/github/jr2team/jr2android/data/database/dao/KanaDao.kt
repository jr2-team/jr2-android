package io.github.jr2team.jr2android.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.entity.Kana

@Dao
abstract class KanaDao : BaseDao<Kana> {
    @Query("SELECT * FROM Kana")
    abstract suspend fun getAll(): List<Kana>
}