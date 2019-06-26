package io.github.jr2team.jr2android.domain.usecase

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.SectionDbRepository
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups

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