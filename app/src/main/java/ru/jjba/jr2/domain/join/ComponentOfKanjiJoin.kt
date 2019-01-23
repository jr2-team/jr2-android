package ru.jjba.jr2.domain.join

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import ru.jjba.jr2.domain.entity.Moji

@Entity(
        foreignKeys = [
            ForeignKey(
                    entity = Moji::class,
                    parentColumns = ["id"],
                    childColumns = ["mojiId"]
            ),
            ForeignKey(
                    entity = Moji::class,
                    parentColumns = ["id"],
                    childColumns = ["mojiComponentId"]
            )
        ]
)
@JsonClass(generateAdapter = true)
class ComponentOfKanjiJoin(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val mojiId: Int = 0,
        val mojiComponentId: Int = 0,
        val order: Short = 0
)