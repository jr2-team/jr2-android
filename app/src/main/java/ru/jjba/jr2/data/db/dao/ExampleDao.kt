package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.Example

@Dao
abstract class ExampleDao {
    @Query("SELECT * FROM Example WHERE interpretation=:interpretationId")
    abstract fun getByInterpretationId(interpretationId: String): Flowable<List<Example>>

    @Query("SELECT * FROM Example")
    abstract fun getAll(): Flowable<List<Example>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(example: Example): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(examples: List<Example>): List<Long>
}