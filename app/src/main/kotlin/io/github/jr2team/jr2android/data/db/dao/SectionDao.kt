package io.github.jr2team.jr2android.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.entity.Section
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups

@Dao
abstract class SectionDao : BaseDao<Section> {
    @Query("SELECT * FROM Section")
    abstract fun getSectionsWithGroups(): List<SectionWithGroups>
}