package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ru.jjba.jr2.domain.entity.Word

@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("SELECT * FROM Word WHERE id=:id")
    abstract fun getById(id: Long): Single<Word>

    @Query("SELECT * FROM Word")
    abstract fun getAll(): Flowable<List<Word>>
}