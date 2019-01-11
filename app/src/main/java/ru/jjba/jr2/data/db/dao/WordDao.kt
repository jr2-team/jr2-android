package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Single
import ru.jjba.jr2.domain.entity.Word

@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("SELECT * FROM Word WHERE id=:id")
    abstract fun getById(id: Int): Single<Word>

    @Query("SELECT * FROM Word")
    abstract fun getAll(): LiveData<List<Word>>

    @Transaction
    @Query("DELETE FROM Word")
    abstract fun deleteAll()

    fun dropAndInsert(words: List<Word>) {
        deleteAll()
        insertMany(words)
    }
}