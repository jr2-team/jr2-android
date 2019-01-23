package ru.jjba.jr2.domain.select

import androidx.room.Embedded
import androidx.room.Relation
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.Section
import ru.jjba.jr2.domain.entity.Word

class SectionWithGroups(
        @Embedded
        val section: Section,
        @Relation(
                parentColumn = "id",
                entityColumn = "sectionId"
        )
        val groups: MutableList<Group>
)