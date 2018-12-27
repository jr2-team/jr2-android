package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
