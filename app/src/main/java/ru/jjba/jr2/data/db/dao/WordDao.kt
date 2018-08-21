package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ru.jjba.jr2.domain.entity.Word

@Dao
abstract class WordDao {
    @Query("SELECT * FROM Word WHERE id=:id")
    abstract fun getById(id: String): Single<Word>

    @Query("SELECT * FROM Word")
    abstract fun getAll(): Single<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(word: Word): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(words: List<Word>): List<Long>
}