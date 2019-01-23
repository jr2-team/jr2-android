package ru.jjba.jr2.presentation.ui.vocab.word.group

import ru.jjba.jr2.domain.entity.Group

class WordGroupSection(
        val sectionTitle: String = "",
        val groups: MutableList<Group> = mutableListOf()
)