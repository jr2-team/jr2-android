package io.github.jr2team.jr2android.domain.usecase

import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.SectionDbRepository
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SectionUseCase(
        private val sectionRepository: SectionDbRepository = SectionDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) {
    suspend fun getWordSections(): List<SectionWithGroups> =
            withContext(Dispatchers.IO) {
                val sections = sectionRepository.getSectionsWithGroups()
                sections.forEach { section ->
                    section.groups.forEach { wordGroup ->
                        wordGroup.itemsCount = groupRepository.getItemsCountInGroup(wordGroup.id)
                    }
                }
                return@withContext sections
            }
}