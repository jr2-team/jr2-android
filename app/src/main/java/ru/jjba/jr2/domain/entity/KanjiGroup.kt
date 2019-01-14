package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
class KanjiGroup(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val groupId: Int,
        val kanjiId: Int,
        val defaultPossition: Int,
        val customPossition: Int
)