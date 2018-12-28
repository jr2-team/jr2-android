package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// TODO: Добавить TypeConverter
@Entity
@JsonClass(generateAdapter = true)
class Word(@PrimaryKey(autoGenerate = true)
           val id: Long = 0,
           val wordJp: String = "",
           val wordFurigana: String = "",
        // TODO: Заменить String -> Interpretation
           val basicInterpretation: String = "",
           val jlptLevel: Int = 5)