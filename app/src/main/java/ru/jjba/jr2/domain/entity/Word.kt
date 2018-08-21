package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class Word(
        @PrimaryKey
        val id: String,
        val wordJp: String,
        val wordFurigana: String,
        val jlptLevel: Int
) {
    @Ignore
    constructor(
            wordJp: String,
            wordFurigana: String,
            jlptLevel: Int
    ) : this(UUID.randomUUID().toString(), wordJp, wordFurigana, jlptLevel)

    override fun toString() = "$id $wordJp $wordFurigana $jlptLevel"
}