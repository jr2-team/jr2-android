package ru.jjba.jr2.domain.interactor

import io.reactivex.Completable
import ru.jjba.jr2.data.repository.kana.KanaDbRepository
import ru.jjba.jr2.domain.entity.Kana

class KanaInteractor(
        private val kanaDbRepository: KanaDbRepository = KanaDbRepository()
) {
    fun getAllKana(): List<Kana> = kanaDbRepository.kana

    fun getKana(index: Int): Kana = kanaDbRepository.kana[index]

    fun insertPiecesOfKana(piecesOfKana: List<Kana>): Completable =
            kanaDbRepository.insert(piecesOfKana)
}