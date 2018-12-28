package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.Interpretation

@Dao
abstract class InterpDao {
    //@Query("SELECT * FROM Interpretation WHERE word_id=:wordId")
    @Query("SELECT * FROM Interpretation WHERE word=:wordId")
    abstract fun getByWordId(wordId: Long): Flowable<List<Interpretation>>

    @Query("SELECT * FROM Interpretation")
    abstract fun getAll(): Flowable<List<Interpretation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(interpretation: Interpretation): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(interpretations: List<Interpretation>): List<Long>
}
