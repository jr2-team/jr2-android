package ru.jjba.jr2.domain.interactor

import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.data.repository.example.ExampleDbRepository
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Example
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word

class WordInteractor(
        private val wordDbRepository: WordDbRepository = WordDbRepository(),
        private val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository(),
        private val exampleDbRepository: ExampleDbRepository = ExampleDbRepository()
) {
    fun getAllWords() = wordDbRepository.getAll()

    fun insertWord(word: Word, interpretationMap: Map<Interpretation, List<Example>>): Completable =
            wordDbRepository.insert(word)
                    .doOnComplete {
                        interpretationMap.forEach {
                            insertInterpretation(it.key, it.value).subscribeBy()
                        }
                    }

    fun insertInterpretation(interpretation: Interpretation, examples: List<Example>): Completable =
            interpretationDbRepository.insert(interpretation)
                    .doOnComplete {
                        examples.forEach {
                            insertExample(it).subscribeBy()
                        }
                    }

    fun insertExample(example: Example): Completable =
            exampleDbRepository.insert(example)
}
