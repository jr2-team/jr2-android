package io.github.jr2team.jr2android.domain.room_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import io.github.jr2team.jr2android.domain.enums.KanjiType

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
    val mojiType: KanjiType = KanjiType.KANJI
)