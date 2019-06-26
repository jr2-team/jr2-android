package io.github.jr2team.jr2android.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.entity.Sentence

@Dao
abstract class SentenceDao : BaseDao<Sentence> {
    @Query("SELECT * FROM Sentence WHERE id = :sentenceId")
    abstract fun getById(sentenceId: Int): Sentence

    @Query("SELECT * FROM Sentence")
    abstract fun getAll(): List<Sentence>

    @Query(// @formatter:off
        "SELECT * FROM Sentence AS s " +
        "INNER JOIN WordInSentenceJoin AS ws " +
            "ON s.id = ws.sentenceId " +
        "WHERE ws.wordId = :wordId"// @formatter:on
    )
    abstract fun getSentencesByWordId(wordId: Int): List<Sentence>
}