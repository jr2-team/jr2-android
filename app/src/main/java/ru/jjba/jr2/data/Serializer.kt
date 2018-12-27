package ru.jjba.jr2.data

import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word

object Serializer {
    /*val word = JsonSerializer<Word> { word, _, _ ->
        JsonObject().apply {
            addProperty("id", word.id)
            addProperty("wordJp", word.wordJp)
            addProperty("wordFurigana", word.wordFurigana)
            addProperty("jlptLevel", word.jlptLevel)
        }
    }

    val interpretation = JsonSerializer<Interp> { interpretation, _, _ ->
        JsonObject().apply {
            addProperty("id", interpretation.id)
            addProperty("interp", interpretation.interp)
            addProperty("pos", interpretation.pos)
            //addProperty("wordId", interp.word.id)
            addProperty("wordId", interpretation.word)
        }
    }*/
}