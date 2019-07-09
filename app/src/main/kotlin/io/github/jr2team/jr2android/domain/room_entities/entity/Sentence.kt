package io.github.jr2team.jr2android.domain.room_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sentence(
    @PrimaryKey
    val id: Int,
    val value: String,
    val furigana: String
)