package ru.jjba.jr2.domain.usecase

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.data.repository.SectionDbRepository
import ru.jjba.jr2.domain.select.SectionWithGroups

class SectionUseCase(
        private val sectionRepository: SectionDbRepository = SectionDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) {
    suspend fun getWordSections(): List<SectionWithGroups> = coroutineScope {
        withContext(coroutineContext) {
            sectionRepository.getSectionsWithGroups().await()
        }.apply {
            forEach { section ->
                section.groups.forEach { wordGroup ->
                    wordGroup.itemsCount = withContext(coroutineContext) {
                        groupRepository.getItemsCountInGroup(wordGroup.id).await()
                    }
                }
            }
        }
    }

}