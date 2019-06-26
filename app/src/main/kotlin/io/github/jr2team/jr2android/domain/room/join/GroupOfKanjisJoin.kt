package io.github.jr2team.jr2android.domain.room.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.entity.Moji

@Entity(foreignKeys = [
    ForeignKey(entity = Group::class,
            parentColumns = ["id"],
            childColumns = ["groupId"]),
    ForeignKey(entity = Moji::class,
            parentColumns = ["id"],
            childColumns = ["mojiId"])
])
class GroupOfKanjisJoin(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val groupId: Int,
        val kanjiId: Int,
        val defaultPossition: Int,
        val customPossition: Int
)