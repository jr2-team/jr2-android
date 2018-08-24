package ru.jjba.jr2.data

import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word

object Serializer {
    val word = JsonSerializer<Word> { word, _, _ ->
        JsonObject().apply {
            addProperty("id", word.id)
            addProperty("wordJp", word.wordJp)
            addProperty("wordFurigana", word.wordFurigana)
            addProperty("jlptLevel", word.jlptLevel)
        }
    }

    val interpretation = JsonSerializer<Interpretation> { interpretation, _, _ ->
        JsonObject().apply {
            addProperty("id", interpretation.id)
            addProperty("interpretation", interpretation.interpretation)
            addProperty("pos", interpretation.pos)
            //addProperty("wordId", interpretation.word.id)
            addProperty("wordId", interpretation.word)
        }
    }
}