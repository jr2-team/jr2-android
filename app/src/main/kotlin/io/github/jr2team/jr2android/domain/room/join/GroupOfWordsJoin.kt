package io.github.jr2team.jr2android.domain.room.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.entity.Word

@Entity(foreignKeys = [
    ForeignKey(
        entity = Group::class,
        parentColumns = ["id"],
        childColumns = ["groupId"]
    ),
    ForeignKey(
        entity = Word::class,
        parentColumns = ["id"],
        childColumns = ["wordId"]
    )
])
class GroupOfWordsJoin(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val groupId: Int,
    val wordId: Int,
    val defaultPosition: Int = 0,
    val customPosition: Int = 0
)