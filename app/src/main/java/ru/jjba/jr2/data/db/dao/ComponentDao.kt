package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Component
import ru.jjba.jr2.domain.entity.Kanji

@Dao
abstract class ComponentDao : BaseDao<Component> {
    @Query("SELECT * FROM Component")
    abstract fun getAll(): LiveData<List<Component>>
}