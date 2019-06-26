package io.github.jr2team.jr2android.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sentence(
        @PrimaryKey
        val id: Int,
        val value: String,
        val furigana: String
)