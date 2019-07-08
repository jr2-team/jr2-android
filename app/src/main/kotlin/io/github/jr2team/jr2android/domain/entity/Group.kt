package io.github.jr2team.jr2android.domain.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.github.jr2team.jr2android.domain.enums.GroupType

@Entity
class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = String(),
    val isUserGroup: Boolean = false,
    val groupType: GroupType = GroupType.KANJI_GROUP,
    val sectionId: Int = 0
    //val creationDate: DateTime,
    //val lastStudyDate: DateTime
) {
    @Ignore
    var itemsCount: Int = 0
}