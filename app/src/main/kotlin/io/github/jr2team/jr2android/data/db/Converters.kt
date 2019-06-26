package io.github.jr2team.jr2android.data.db

import androidx.room.TypeConverter
import io.github.jr2team.jr2android.domain.entity.GroupType
import io.github.jr2team.jr2android.domain.entity.KanaType
import io.github.jr2team.jr2android.domain.entity.MojiType

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