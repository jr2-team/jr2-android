package ru.jjba.jr2.domain.interactor

import ru.jjba.jr2.data.repository.word.WordDbRepository

class WordInteractor(
       private val dbRepository: WordDbRepository = WordDbRepository()
) {

}