package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Interp(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var interp: String,
        var pos: String,
        //@Embedded(prefix = "word_")
        var word: Long
) {
    override fun toString() = "$id $pos $interp"
}