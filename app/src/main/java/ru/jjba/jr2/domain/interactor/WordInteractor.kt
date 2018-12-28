package ru.jjba.jr2.domain.interactor

import io.reactivex.Completable
import ru.jjba.jr2.data.repository.ExampleDbRepository
import ru.jjba.jr2.data.repository.InterpretationDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word

class WordInteractor(
        private val wordDbRepository: WordDbRepository = WordDbRepository(),
        private val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository(),
        private val exampleDbRepository: ExampleDbRepository = ExampleDbRepository()
) {
    fun getAllWords() = wordDbRepository.getAll()

    fun getWordById(wordId: Long) = wordDbRepository.getById(wordId)

    fun insert(words: List<Word>): Completable = wordDbRepository.insert(words)
}
