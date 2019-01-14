package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [
            ForeignKey(
                    entity = Kanji::class,
                    parentColumns = ["id"],
                    childColumns = ["idKanji"]
            ),
            ForeignKey(
                    entity = Kanji::class,
                    parentColumns = ["id"],
                    childColumns = ["idKanjiComponent"]
            )
        ]
)
class Component(
        @PrimaryKey
        val id: Int,
        val idKanji: Int,
        val idKanjiComponent: Int
)