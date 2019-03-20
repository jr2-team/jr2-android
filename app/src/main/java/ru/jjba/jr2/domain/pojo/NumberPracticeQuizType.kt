package ru.jjba.jr2.domain.pojo

import org.jetbrains.anko.collections.forEachWithIndex

// TODO: Find a better solution to store number practice types
@Suppress("NAME_SHADOWING")
class NumberPracticeQuizType(
        val code: Int,
        val humanReadable: String,
        var isChecked: Boolean = true
) {
    override fun toString() = humanReadable

    companion object {
        val HIROGANA_KANJI = NumberPracticeQuizType(0, "Хирогана → Кандзи")
        val HIROGANA_NUMBER = NumberPracticeQuizType(1, "Хирогана → Число")
        val KANJI_HIROGANA = NumberPracticeQuizType(2, "Кандзи → Хирогана")
        val KANJI_NUMBER = NumberPracticeQuizType(3, "Кандзи → Число")
        val NUMBER_HIROGANA = NumberPracticeQuizType(4, "Число → Хирогана")
        val NUMBER_KANJI = NumberPracticeQuizType(5, "Число → Кандзи")

        const val defaultState = "111111"
        const val minimalState = "100000"
        const val illegalState = "000000"

        fun getTypes(checkState: String = String()): List<NumberPracticeQuizType> {
            val isCheckStateValid =
                    checkState.matches(Regex("^[0-1]{6}$")) && checkState != illegalState
            val checkState = if (isCheckStateValid) checkState else minimalState
            return listOf(
                    HIROGANA_KANJI, HIROGANA_NUMBER,
                    KANJI_HIROGANA, KANJI_NUMBER,
                    NUMBER_HIROGANA, NUMBER_KANJI
            ).apply {
                forEachWithIndex { idx, type -> type.isChecked = checkState[idx] == '1' }
            }
        }
    }
}