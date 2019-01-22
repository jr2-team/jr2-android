package ru.jjba.jr2.domain.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.Kanji

@Entity(
        foreignKeys = [
            ForeignKey(
                    entity = Group::class,
                    parentColumns = ["id"],
                    childColumns = ["groupId"]
            ),
            ForeignKey(
                    entity = Kanji::class,
                    parentColumns = ["id"],
                    childColumns = ["kanjiId"]
            )
        ]
)
class GroupOfKanjiJoin(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val groupId: Int,
        val kanjiId: Int,
        val defaultPossition: Int,
        val customPossition: Int
)