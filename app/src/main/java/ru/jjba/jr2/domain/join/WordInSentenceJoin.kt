package ru.jjba.jr2.domain.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.jjba.jr2.domain.entity.Sentence
import ru.jjba.jr2.domain.entity.Word

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
class WordInSentenceJoin(
        @PrimaryKey
        val id: Int,
        val wordId: Int,
        val sentenceId: Int,
        val pos: String
)