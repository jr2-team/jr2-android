package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.Example

@Dao
abstract class ExampleDao {
    @Query("SELECT * FROM Example WHERE interp=:interpId")
    abstract fun getByInterpId(interpId: String): Flowable<List<Example>>

    @Query("SELECT * FROM Example")
    abstract fun getAll(): Flowable<List<Example>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(example: Example): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(examples: List<Example>): List<Long>
}