package io.github.jr2team.jr2android.data.database.dao

import androidx.room.*
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.domain.room.join.GroupOfWordsJoin

@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("SELECT * FROM Word WHERE id = :wordId")
    abstract suspend fun getById(wordId: Int): Word

    @Query("SELECT * FROM Word")
    abstract suspend fun getAll(): List<Word>

    @Query("""
        SELECT w.* 
        FROM Word AS w 
            INNER JOIN GroupOfWordsJoin AS wj ON w.id == wj.wordId 
        WHERE wj.groupId = :groupId"""
    )
    abstract suspend fun getWordsByGroupId(groupId: Int): List<Word>

    @Insert
    abstract suspend fun insertWordIntoGroup(groupOfWordJoin: List<GroupOfWordsJoin>)

    @Query("DELETE FROM Word")
    abstract fun deleteAll()

    @Transaction
    open suspend fun dropAndInsert(words: List<Word>) {
        deleteAll()
        insertMany(words)
    }
}