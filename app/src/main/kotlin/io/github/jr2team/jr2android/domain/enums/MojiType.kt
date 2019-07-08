package io.github.jr2team.jr2android.domain.enums

enum class MojiType(val code: Int) {
    RADICAL(0),
    KANJI(1);

    companion object {
        fun fromCode(code: Int): MojiType = when (code) {
            RADICAL.code -> RADICAL
            KANJI.code -> KANJI
            else -> throw IllegalArgumentException()
        }
    }
}