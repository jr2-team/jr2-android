package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity
class Group(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String = String(),
        val isUserGroup: Boolean = false,
        val groupType: GroupType = GroupType.KANJI_GROUP,
        val groupSection: GroupSection = GroupSection.CUSTOM
        //val creationDate: DateTime,
        //val lastStudyDate: DateTime
)

enum class GroupType(val code: Int) {
    KANJI_GROUP(0),
    WORD_GROUP(1);

    companion object {
        fun fromCode(code: Int) = when(code) {
            KANJI_GROUP.code -> KANJI_GROUP
            WORD_GROUP.code -> WORD_GROUP
            else -> throw IllegalArgumentException()
        }
    }
}

enum class GroupSection(val code: Int) {
    CUSTOM(0),
    JLPT_1(1),
    JLPT_2(2),
    JLPT_3(3),
    JLPT_4(4),
    JLPT_5(5);

    companion object {
        fun fromCode(code: Int) = when(code) {
            CUSTOM.code -> CUSTOM
            JLPT_1.code -> JLPT_1
            JLPT_2.code -> JLPT_2
            JLPT_3.code -> JLPT_3
            JLPT_4.code -> JLPT_4
            JLPT_5.code -> JLPT_5
            else -> throw IllegalArgumentException()
        }
    }
}