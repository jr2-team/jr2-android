package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.jjba.jr2.domain.entity.Sentence

@Dao
abstract class SentenceDao : BaseDao<Sentence> {
    @Query("SELECT * FROM Sentence WHERE id = :sentenceId")
    abstract fun getById(sentenceId: Int): LiveData<Sentence>

    @Query("SELECT * FROM Sentence")
    abstract fun getAll(): LiveData<List<Sentence>>
}