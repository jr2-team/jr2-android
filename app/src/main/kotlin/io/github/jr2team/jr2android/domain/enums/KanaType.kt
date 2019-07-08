package io.github.jr2team.jr2android.domain.enums

enum class KanaType(val code: Int) {
    HIROGANA(0),
    KATAKANA(1);

    companion object {
        fun fromCode(code: Int): KanaType = when(code) {
            HIROGANA.code -> HIROGANA
            KATAKANA.code -> KATAKANA
            else -> throw IllegalArgumentException()
        }
    }
}