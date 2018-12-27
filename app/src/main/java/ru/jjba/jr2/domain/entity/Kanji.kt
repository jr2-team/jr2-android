package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Kanji(
        @PrimaryKey
        val id: String,
        val kanjiChar: String,
        val kunReading: String,
        val onReading: String,
        val jlptLevel: Int,
        val strokeCount: Int,
        val isRadical: Boolean
) {
    //TODO : Выпилить конструктор
    @Ignore
    constructor(
            id: String,
            kanjiChar: String
    ) : this(id, kanjiChar, "", "", 0, 0, false)

    override fun toString() = "$id $kanjiChar $kunReading $onReading"
}

@Entity
class KanjiPart(
        @PrimaryKey
        val id: String,
        val kanji: String,
        val kanjiPart: String,
        val position: Int
)