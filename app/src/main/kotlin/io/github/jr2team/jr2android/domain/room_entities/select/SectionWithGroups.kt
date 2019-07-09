package io.github.jr2team.jr2android.domain.room_entities.select

import androidx.room.Embedded
import androidx.room.Relation
import io.github.jr2team.jr2android.domain.room_entities.entity.Group
import io.github.jr2team.jr2android.domain.room_entities.entity.Section

class SectionWithGroups(
    @Embedded
    val section: Section,
    @Relation(parentColumn = "id", entityColumn = "sectionId")
    val groups: List<Group>
)