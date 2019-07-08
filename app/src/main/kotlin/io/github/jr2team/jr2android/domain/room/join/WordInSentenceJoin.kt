package io.github.jr2team.jr2android.domain.room.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.github.jr2team.jr2android.domain.entity.Sentence
import io.github.jr2team.jr2android.domain.entity.Word

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
    // TODO : Добавить enum для POS
    val pos: String
)