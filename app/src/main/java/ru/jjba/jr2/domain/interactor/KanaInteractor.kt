package ru.jjba.jr2.domain.interactor

import ru.jjba.jr2.data.repository.kana.KanaRepository
import ru.jjba.jr2.domain.entity.JpSound

class KanaInteractor(
        private val kanaRepository: KanaRepository = KanaRepository()
) {
    fun getAllKana(): List<JpSound> = kanaRepository.kana

    fun getKana(index: Int): JpSound = kanaRepository.kana[index]
}