package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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
    override fun toString() = "$id $kanjiChar $kunReading $onReading"
}

@Entity
class KanjiPart(
    val kanji: String,
    val kanjiPart: String
)