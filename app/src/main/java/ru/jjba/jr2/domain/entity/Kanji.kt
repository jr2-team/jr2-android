package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: Придумать навание получше, хранить в одной таблице и канджи и радикалы?
@Entity
class Kanji(
        @PrimaryKey
        val id: Int,
        val value: Char,
        val strokeCount: Short,
        val onReading: String,
        val kunReading: String,
        val basicInterpretation: String,
        val jlptLevel: Short,
        val isRadical: Boolean = false
)
