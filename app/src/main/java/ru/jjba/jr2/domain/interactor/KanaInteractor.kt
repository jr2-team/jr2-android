package ru.jjba.jr2.domain.interactor

import io.reactivex.Completable
import ru.jjba.jr2.data.repository.KanaDbRepository
import ru.jjba.jr2.domain.entity.Kana

class KanaInteractor(
        private val kanaDbRepository: KanaDbRepository = KanaDbRepository()
) {
    fun getAllKana() = kanaDbRepository.getAll()

    fun getSpecificKana(isNigoriMode: Boolean = false) =
            if (isNigoriMode) kanaDbRepository.getOnlyAdditionalSound() else kanaDbRepository.getOnlyKana()

    fun getKana(kanaId: String) = kanaDbRepository.getKana(kanaId)

    fun insertPiecesOfKana(piecesOfKana: List<Kana>): Completable =
            kanaDbRepository.insert(piecesOfKana)
}