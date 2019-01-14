package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Group(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String = String(),
        val isUserGroup: Boolean = false,
        val groupTyle: GroupType = GroupType.KANJI_GROUP
        //TODO: Добавить поля: CreationDate, LastStudyDate
)

enum class GroupType{
        KANJI_GROUP,
        WORD_GROUP
}