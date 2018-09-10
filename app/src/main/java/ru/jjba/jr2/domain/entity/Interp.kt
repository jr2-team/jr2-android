package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

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