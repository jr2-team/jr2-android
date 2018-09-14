package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.Interp

@Dao
abstract class InterpDao {
    //@Query("SELECT * FROM Interp WHERE word_id=:wordId")
    @Query("SELECT * FROM Interp WHERE word=:wordId")
    abstract fun getByWordId(wordId: Long): Flowable<List<Interp>>

    @Query("SELECT * FROM Interp")
    abstract fun getAll(): Flowable<List<Interp>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(interpretation: Interp): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(interpretations: List<Interp>): List<Long>
}