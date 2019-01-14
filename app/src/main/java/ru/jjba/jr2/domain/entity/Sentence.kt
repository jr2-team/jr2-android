package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sentence(
        @PrimaryKey
        val id: Int,
        val value: String,
        val furigana: String
)