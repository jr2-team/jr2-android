package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.Kana

@Dao
abstract class KanaDao {
    @Query("SELECT * FROM Kana")
    abstract fun getAll(): Flowable<List<Kana>>

    @Query("SELECT * FROM Kana WHERE isNigori = 0")
    abstract fun getOnlyKana(): Flowable<List<Kana>>

    @Query("SELECT * FROM Kana WHERE isNigori = 1")
    abstract fun getOnlyAdditionalSound(): Flowable<List<Kana>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(piecesOfKana: List<Kana>): List<Long>
}