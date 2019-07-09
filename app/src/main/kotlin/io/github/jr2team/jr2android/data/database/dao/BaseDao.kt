package io.github.jr2team.jr2android.data.database.dao

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(o: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(o: List<T>): List<Long>

    @Update
    suspend fun update(o: T): Int

    @Delete
    suspend fun delete(o: T): Int
}