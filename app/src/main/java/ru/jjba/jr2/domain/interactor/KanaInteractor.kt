package ru.jjba.jr2.domain.interactor

import ru.jjba.jr2.data.repository.kana.KanaRepository
import ru.jjba.jr2.domain.entity.Kana

class KanaInteractor(
        private val kanaRepository: KanaRepository = KanaRepository()
) {
    fun getAllKana(): List<Kana> = kanaRepository.kana

    fun getKana(index: Int): Kana = kanaRepository.kana[index]
}