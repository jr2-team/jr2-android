package ru.jjba.jr2.presentation.ui.number.domani

import io.github.pnzeml.num2jp.getNumberInKanji
import io.github.pnzeml.num2jp.getNumberInKatakana

data class JpNumber(
        val value: Int,
        val valueStr: String = value.toString()
) {
    val numberKanji = valueStr.getNumberInKanji()
    val numberHirogana = valueStr.getNumberInKatakana()
}