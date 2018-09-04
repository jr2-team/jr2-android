package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Interp(
        @PrimaryKey
        val id: String,
        val interp: String,
        val pos: String,
        @Embedded(prefix = "word_")
        val word: Word
) {
        override fun toString() = "$id $pos $interp"
}