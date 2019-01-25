package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Kana

@Dao
abstract class KanaDao : BaseDao<Kana> {
    @Query("SELECT * FROM Kana")
    abstract fun getAll(): List<Kana>
}