package ru.jjba.jr2.data.db.dao

import androidx.room.*

interface BaseDao<ET> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(o: ET)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(o: List<ET>)

    @Update
    fun update(o: ET)

    @Delete
    fun delete(o: ET)
}