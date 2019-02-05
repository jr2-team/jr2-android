package ru.jjba.jr2.data.db.dao

import androidx.room.*
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.room.join.GroupOfWordsJoin

@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("SELECT * FROM Word WHERE id = :wordId")
    abstract fun getById(wordId: Int): Word

    @Query("SELECT * FROM Word")
    abstract fun getAll(): List<Word>

    @Query(// @formatter:off
        "SELECT w.* FROM Word AS w " +
        "INNER JOIN GroupOfWordsJoin AS wj " +
            "ON w.id == wj.wordId " +
        "WHERE wj.groupId = :groupId"// @formatter:on
    )
    abstract fun getWordsByGroupId(groupId: Int): List<Word>

    @Insert
    abstract fun insertWordIntoGroup(groupOfWordJoin: List<GroupOfWordsJoin>)

    @Query("DELETE FROM Word")
    abstract fun deleteAll()

    @Transaction
    open fun dropAndInsert(words: List<Word>) {
        deleteAll()
        insertMany(words)
    }
}