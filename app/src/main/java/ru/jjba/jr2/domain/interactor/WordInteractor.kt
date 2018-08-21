package ru.jjba.jr2.domain.interactor

import io.reactivex.Observable
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Word

class WordInteractor(
       private val wordDbRepository: WordDbRepository = WordDbRepository()
) {
    fun getAllWords(): Observable<Word> =
            wordDbRepository.getAll()
}