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
        val value: String = String(),
        val furigana: String = String(),
        val basicInterpretation: String = String(),
        val jlptLevel: Int = 5
)