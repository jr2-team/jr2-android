package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Interpretation(
        @PrimaryKey
        val id: String,
        val interpretation: String,
        val pos: String,
        /*@Embedded(prefix = "word_")*/
        val word: String
) {
        override fun toString() = "$id $pos $interpretation"
}