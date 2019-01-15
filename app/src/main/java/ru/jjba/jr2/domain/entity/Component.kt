package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(
        foreignKeys = [
            ForeignKey(
                    entity = Kanji::class,
                    parentColumns = ["id"],
                    childColumns = ["kanjiId"]
            ),
            ForeignKey(
                    entity = Kanji::class,
                    parentColumns = ["id"],
                    childColumns = ["kanjiIdComp"]
            )
        ]
)
@JsonClass(generateAdapter = true)
class Component(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val kanjiId: Int = 0,
        val kanjiIdComp: Int = 0,
        val order: Short = 0
)