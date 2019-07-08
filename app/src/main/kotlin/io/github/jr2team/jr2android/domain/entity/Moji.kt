package io.github.jr2team.jr2android.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import io.github.jr2team.jr2android.domain.enums.MojiType

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