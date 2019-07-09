package io.github.jr2team.jr2android.domain.enums

enum class KanjiType(val code: Int) {
    KANJI(0);

    companion object {
        fun fromCode(code: Int): KanjiType = when (code) {
            KANJI.code -> KANJI
            else -> throw IllegalArgumentException()
        }
    }
}