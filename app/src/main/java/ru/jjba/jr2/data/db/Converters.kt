package ru.jjba.jr2.data.db

import androidx.room.TypeConverter
import ru.jjba.jr2.domain.entity.GroupType
import ru.jjba.jr2.domain.entity.KanaType
import ru.jjba.jr2.domain.entity.MojiType

class Converters {
    @TypeConverter
    fun toKanaType(value: String?): KanaType? = value
            ?.toIntOrNull()
            ?.let { KanaType.fromCode(it) }

    @TypeConverter
    fun fromKanaType(kanaType: KanaType?) =
            kanaType?.code?.toString() ?: ""

    @TypeConverter
    fun toGroupType(value: String?): GroupType? = value
            ?.toIntOrNull()
            ?.let { GroupType.fromCode(it) }

    @TypeConverter
    fun fromGroupType(groupType: GroupType?) =
            groupType?.code?.toString() ?: ""

    @TypeConverter
    fun toMojiType(value: String?): MojiType? = value
            ?.toIntOrNull()
            ?.let { MojiType.fromCode(it) }

    @TypeConverter
    fun fromMojiType(mojiType: MojiType?) =
            mojiType?.code?.toString() ?: ""
}