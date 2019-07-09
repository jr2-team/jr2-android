package io.github.jr2team.jr2android.domain.room_entities.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.github.jr2team.jr2android.domain.room_entities.entity.Sentence
import io.github.jr2team.jr2android.domain.room_entities.entity.Word

@Entity(foreignKeys = [
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
])
class WordInSentenceJoin(
    @PrimaryKey
    val id: Int,
    val wordId: Int,
    val sentenceId: Int,
    val pos: String
)