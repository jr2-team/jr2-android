package ru.jjba.jr2.data.db.dao

import androidx.room.*

interface BaseDao<ET> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(o: ET): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(o: List<ET>): List<Long>

    @Update
    fun update(o: ET): Int

    @Delete
    fun delete(o: ET): Int
}