package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Example(
        @PrimaryKey
        val id: String,
        val exampleJp: String,
        val exampleFurigana: String,
        val exampleTranslate: String,
        /*@Embedded(prefix = "interpretation_")*/
        val interp: String
) {
    override fun toString() = "$id $exampleJp $exampleFurigana $exampleTranslate"
}