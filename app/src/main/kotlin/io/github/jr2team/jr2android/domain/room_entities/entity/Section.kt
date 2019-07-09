package io.github.jr2team.jr2android.domain.room_entities.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Section(
    @PrimaryKey
    val id: Int = 0,
    var name: String = ""
)