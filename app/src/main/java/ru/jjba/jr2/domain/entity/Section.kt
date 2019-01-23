package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Section(
        @PrimaryKey
        val id: Int = 0,
        val name: String = ""
)