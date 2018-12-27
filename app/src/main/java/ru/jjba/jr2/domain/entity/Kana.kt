package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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