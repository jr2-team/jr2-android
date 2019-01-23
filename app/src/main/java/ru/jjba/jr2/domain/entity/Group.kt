package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Group(
        @PrimaryKey
        val id: Int = 0,
        val name: String = String(),
        val isUserGroup: Boolean = false,
        val groupType: GroupType = GroupType.KANJI_GROUP,
        val sectionId: Int = 0
        //val creationDate: DateTime,
        //val lastStudyDate: DateTime
)

enum class GroupType(val code: Int) {
    KANJI_GROUP(0),
    WORD_GROUP(1);

    companion object {
        fun fromCode(code: Int) = when (code) {
            KANJI_GROUP.code -> KANJI_GROUP
            WORD_GROUP.code -> WORD_GROUP
            else -> throw IllegalArgumentException()
        }
    }
}