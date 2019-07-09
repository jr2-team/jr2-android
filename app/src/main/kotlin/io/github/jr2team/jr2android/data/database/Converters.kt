package io.github.jr2team.jr2android.data.database

import androidx.room.TypeConverter
import io.github.jr2team.jr2android.domain.enums.KanjiType

class Converters {
    @TypeConverter
    fun toKanjiType(value: String?): KanjiType? = value
        ?.toIntOrNull()
        ?.let { KanjiType.fromCode(it) }

    @TypeConverter
    fun fromKanjiType(kanjiType: KanjiType?) =
        kanjiType?.code?.toString() ?: ""
}