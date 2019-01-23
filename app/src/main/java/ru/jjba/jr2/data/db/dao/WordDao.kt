package ru.jjba.jr2.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.join.GroupOfWordJoin

@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("SELECT * FROM Word WHERE id = :wordId")
    abstract fun getById(wordId: Int): LiveData<Word>

    @Query("SELECT * FROM Word")
    abstract fun getAll(): LiveData<List<Word>>

    @Query(// @formatter:off
        "SELECT * FROM Word AS w " +
        "INNER JOIN GroupOfWordJoin AS wj " +
            "ON w.id == wj.wordId " +
        "WHERE wj.groupId = :groupId"// @formatter:on
    )
    abstract fun getWordsByGroupId(groupId: Int): List<Word>

    @Insert
    abstract fun insertWordIntoGroup(groupOfWordJoin: List<GroupOfWordJoin>)

    @Query("DELETE FROM Word")
    abstract fun deleteAll()

    @Transaction
    open fun dropAndInsert(words: List<Word>) {
        deleteAll()
        insertMany(words)
    }
}