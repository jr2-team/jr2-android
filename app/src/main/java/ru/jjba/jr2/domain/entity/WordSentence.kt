package ru.jjba.jr2.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [
                ForeignKey(
                        entity = Word::class,
                        parentColumns = ["id"],
                        childColumns = ["wordId"]
                ),
                ForeignKey(
                        entity = Sentence::class,
                        parentColumns = ["id"],
                        childColumns = ["sentenceId"]
                )
        ]
)
class WordSentence(
        @PrimaryKey
        val id: Int,
        val wordId: Int,
        val sentenceId: Int,
        val pos: String
)