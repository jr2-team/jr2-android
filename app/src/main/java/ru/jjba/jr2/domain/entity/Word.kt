package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//TODO : Добавить TypeConverter
@Entity
class Word(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var wordJp: String,
        var wordFurigana: String,
        var basicInterp: String,
        var jlptLevel: Int,
        @Ignore val interps: MutableList<Interp>
) {
    constructor() : this(0, "", "", "", 0, mutableListOf())

    override fun toString() = "$id $wordJp $wordFurigana $jlptLevel"
}