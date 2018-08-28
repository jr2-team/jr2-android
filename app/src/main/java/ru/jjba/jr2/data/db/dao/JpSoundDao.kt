package ru.jjba.jr2.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.jjba.jr2.domain.entity.JpSound

@Dao
abstract class JpSoundDao {
    @Query("SELECT * FROM JpSound")
    abstract fun getAll(): Flowable<List<JpSound>>

    @Query("SELECT * FROM JpSound WHERE isAdditionalSound = 0")
    abstract fun getOnlyKana(): Flowable<List<JpSound>>

    @Query("SELECT * FROM JpSound WHERE isAdditionalSound = 1")
    abstract fun getOnlyAdditionalSound(): Flowable<List<JpSound>>
}