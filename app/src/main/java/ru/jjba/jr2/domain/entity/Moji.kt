package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
class Moji(
        @PrimaryKey
        val id: Int = 0,
        val value: Char = Char.MIN_VALUE,
        val strokeCount: Short = 0,
        val onReading: String = "",
        val kunReading: String = "",
        val basicInterpretation: String = "",
        val jlptLevel: Short = 5,
        val mojiType: MojiType = MojiType.KANJI
)

enum class MojiType(val code: Int) {
    RADICAL(0),
    KANJI(1);

    companion object {
        fun fromCode(code: Int): MojiType = when (code) {
            RADICAL.code -> RADICAL
            KANJI.code -> KANJI
            else -> throw IllegalArgumentException()
        }
    }
}