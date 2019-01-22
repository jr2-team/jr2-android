package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.jjba.jr2.domain.entity.Word

@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("SELECT * FROM Word WHERE id = :wordId")
    abstract fun getById(wordId: Int): LiveData<Word>

    @Query("SELECT * FROM Word")
    abstract fun getAll(): LiveData<List<Word>>

    @Query("SELECT * FROM Word as w WHERE w.basicInterpretation LIKE '%' || :query || '%'")
    abstract fun getByQuery(query: String): LiveData<List<Word>>

    @Query("DELETE FROM Word")
    abstract fun deleteAll()

    @Transaction
    open fun dropAndInsert(words: List<Word>) {
        deleteAll()
        insertMany(words)
    }
}