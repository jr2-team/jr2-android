package ru.jjba.jr2.domain.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.Word

@Entity(
        foreignKeys = [
            ForeignKey(
                    entity = Group::class,
                    parentColumns = ["id"],
                    childColumns = ["groupId"]
            ),
            ForeignKey(
                    entity = Word::class,
                    parentColumns = ["id"],
                    childColumns = ["wordId"]
            )
        ]
)
class GroupOfWordsJoin(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val groupId: Int,
        val wordId: Int,
        val defaultPossition: Int = 0,
        val customPossition: Int = 0
)