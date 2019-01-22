package ru.jjba.jr2.data.db

import androidx.room.TypeConverter
import ru.jjba.jr2.domain.entity.GroupSection
import ru.jjba.jr2.domain.entity.GroupType
import ru.jjba.jr2.domain.entity.KanaType

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
    fun toGroupSection(value: String?): GroupSection? = value
            ?.toIntOrNull()
            ?.let { GroupSection.fromCode(it) }

    @TypeConverter
    fun fromGroupSection(groupSection: GroupSection?) =
            groupSection?.code?.toString() ?: ""
}