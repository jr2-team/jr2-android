package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class Word(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var wordJp: String,
        var wordFurigana: String,
        var jlptLevel: Int,
        @Ignore val interps: MutableList<Interp>
) {
    constructor() : this(0, "", "", 0, mutableListOf())

    @Ignore
    constructor(
            wordJp: String,
            wordFurigana: String,
            jlptLevel: Int
    ) : this(0, wordJp, wordFurigana, jlptLevel, mutableListOf())

    override fun toString() = "$id $wordJp $wordFurigana $jlptLevel"
}