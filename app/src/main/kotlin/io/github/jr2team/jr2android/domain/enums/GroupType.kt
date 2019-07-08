package io.github.jr2team.jr2android.domain.enums

enum class GroupType(val code: Int) {
    KANJI_GROUP(0),
    WORD_GROUP(1);

    companion object {
        fun fromCode(code: Int) = when (code) {
            KANJI_GROUP.code -> KANJI_GROUP
            WORD_GROUP.code -> WORD_GROUP
            else -> throw IllegalArgumentException()
        }
    }
}