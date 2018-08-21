package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.Interpretation

@Dao
abstract class InterpretationDao {
    @Query("SELECT * FROM Interpretation WHERE word_id=:id")
    abstract fun getByWordId(id: String): Flowable<List<Interpretation>>

    @Query("SELECT * FROM Interpretation")
    abstract fun getAll(): Flowable<List<Interpretation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(interpretation: Interpretation): Long
}
