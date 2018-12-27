package ru.jjba.jr2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ru.jjba.jr2.domain.entity.Kana

@Dao
abstract class KanaDao {
    @Query("SELECT * FROM Kana")
    abstract fun getAll(): Flowable<List<Kana>>

    @Query("SELECT * FROM Kana WHERE isNigori = 0")
    abstract fun getOnlyKana(): Flowable<List<Kana>>

    @Query("SELECT * FROM Kana WHERE isNigori = 1")
    abstract fun getOnlyAdditionalSound(): Flowable<List<Kana>>

    @Query("SELECT * FROM Kana WHERE id = :kanaId")
    abstract fun getKana(kanaId: String): Single<Kana>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(piecesOfKana: List<Kana>): List<Long>
}