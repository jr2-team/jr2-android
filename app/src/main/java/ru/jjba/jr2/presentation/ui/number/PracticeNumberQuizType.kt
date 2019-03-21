package ru.jjba.jr2.presentation.ui.number

enum class PracticeNumberQuizType(val code: Int) {
    HIROGANA_KANJI(0),
    HIROGANA_NUMBER(1),
    KANJI_HIROGANA(2),
    KANJI_NUMBER(3),
    NUMBER_HIROGANA(4),
    NUMBER_KANJI(5);

    companion object {
        fun getRandom(vararg codes: Int) =
                fromCode(codes.toList().shuffled().first())

        fun fromCode(code: Int): PracticeNumberQuizType {
            return when (code) {
                HIROGANA_KANJI.code -> HIROGANA_KANJI
                HIROGANA_NUMBER.code -> HIROGANA_NUMBER
                KANJI_HIROGANA.code -> KANJI_HIROGANA
                KANJI_NUMBER.code -> KANJI_NUMBER
                NUMBER_HIROGANA.code -> NUMBER_HIROGANA
                NUMBER_KANJI.code -> NUMBER_KANJI
                else -> throw IllegalArgumentException()
            }
        }
    }
}