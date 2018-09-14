package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

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