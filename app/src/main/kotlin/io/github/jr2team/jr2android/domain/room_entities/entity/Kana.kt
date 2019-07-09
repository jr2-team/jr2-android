package io.github.jr2team.jr2android.domain.room_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import io.github.jr2team.jr2android.domain.enums.KanaType

@Entity
@JsonClass(generateAdapter = true)
class Kana(
    @PrimaryKey
    val id: Int = 0,
    val value: String = "",
    val engReading: String = "",
    val rusReading: String = "",
    val kanaType: KanaType = KanaType.HIROGANA
)