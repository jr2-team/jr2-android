package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

// TODO: Придумать навание получше, хранить в одной таблице и канджи и радикалы?
@Entity
@JsonClass(generateAdapter = true)
class Kanji(
        @PrimaryKey
        val id: Int = 0,
        val value: Char = Char.MIN_VALUE,
        val strokeCount: Short = 0,
        val onReading: String = "",
        val kunReading: String = "",
        val basicInterpretation: String = "",
        val jlptLevel: Short = 5,
        val isRadical: Boolean = false
)
