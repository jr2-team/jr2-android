package io.github.jr2team.jr2android.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.room_entities.entity.Section
import io.github.jr2team.jr2android.domain.room_entities.select.SectionWithGroups

@Dao
abstract class SectionDao : BaseDao<Section> {
    @Query("SELECT * FROM Section")
    abstract suspend fun getSectionsWithGroups(): List<SectionWithGroups>
}