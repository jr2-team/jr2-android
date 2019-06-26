package io.github.jr2team.jr2android.domain.room.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import io.github.jr2team.jr2android.domain.entity.Moji

@Entity(foreignKeys = [
    ForeignKey(entity = Moji::class,
            parentColumns = ["id"],
            childColumns = ["mojiId"]),
    ForeignKey(entity = Moji::class,
            parentColumns = ["id"],
            childColumns = ["mojiComponentId"])
])
@JsonClass(generateAdapter = true)
class ComponentOfKanjiJoin(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val mojiId: Int = 0,
        val mojiComponentId: Int = 0,
        val order: Short = 0
)