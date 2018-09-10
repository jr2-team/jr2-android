package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class Word(
        @PrimaryKey
        var id: String,
        var wordJp: String,
        var wordFurigana: String,
        var jlptLevel: Int,
        @Ignore val listOfInterps: MutableList<Interp>
) {
    constructor() : this(UUID.randomUUID().toString(), "", "", 0, mutableListOf())

    @Ignore
    constructor(
            wordJp: String,
            wordFurigana: String,
            jlptLevel: Int
    ) : this(UUID.randomUUID().toString(), wordJp, wordFurigana, jlptLevel, mutableListOf())

    override fun toString() = "$id $wordJp $wordFurigana $jlptLevel"
}