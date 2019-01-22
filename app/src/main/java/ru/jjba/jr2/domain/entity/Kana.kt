package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

// TODO: Исправить KanaType
@Entity
@JsonClass(generateAdapter = true)
class Kana(
        @PrimaryKey
        val id: Int = 0,
        val value: String = String(),
        val engReading: String = String(),
        val rusReading: String = String(),
        val kanaType: KanaType = KanaType.HIROGANA
)

enum class KanaType(val code: Int) {
    HIROGANA(0),
    KATAKANA(1);

    companion object {
        fun fromCode(code: Int): KanaType = when(code) {
            HIROGANA.code -> HIROGANA
            KATAKANA.code -> KATAKANA
            else -> throw IllegalArgumentException()
        }
    }
}

