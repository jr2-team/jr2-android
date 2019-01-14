package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

// TODO: Добавить TypeConverter
@Entity
@JsonClass(generateAdapter = true)
class Word(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val value: String = "",
        val furigana: String = "",
        val basicInterpretation: String = "",
        val jlptLevel: Int = 5
)