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
                    entity = Word::class,
                    parentColumns = ["id"],
                    childColumns = ["wordId"]
            )
        ]
)
class WordGroup(
        @PrimaryKey
        val id: Int,
        val groupId: Int,
        val wordId: Int,
        val defaultPossition: Int,
        val customPossition: Int
)