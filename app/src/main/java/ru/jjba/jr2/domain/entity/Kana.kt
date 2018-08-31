package ru.jjba.jr2.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Kana(
        @PrimaryKey
        val id: String,
        val hiragana: String,
        val katakana: String,
        val eng: String,
        val rus: String,
        val isNigori: Boolean
)