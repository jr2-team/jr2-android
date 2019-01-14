package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Sentence
import ru.jjba.jr2.domain.entity.WordSentence

@Dao
abstract class WordSentenceDao : BaseDao<WordSentence> {
    @Query(
            // @formatter:off
            "SELECT * FROM Sentence AS s " +
            "INNER JOIN WordSentence AS ws " +
                "ON s.id = ws.sentenceId " +
            "WHERE ws.wordId = :wordId"
            // @formatter:on
    )
    abstract fun getWordSentences(wordId: Int): LiveData<List<Sentence>>
}