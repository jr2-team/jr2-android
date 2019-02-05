package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Section
import ru.jjba.jr2.domain.room.select.SectionWithGroups

@Dao
abstract class SectionDao : BaseDao<Section> {
    @Query("SELECT * FROM Section")
    abstract fun getSectionsWithGroups(): List<SectionWithGroups>
}